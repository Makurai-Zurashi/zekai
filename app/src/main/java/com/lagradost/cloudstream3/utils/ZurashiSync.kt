package com.lagradost.cloudstream3.utils

import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.CloudStreamApp.Companion.getKey
import com.lagradost.cloudstream3.CloudStreamApp.Companion.setKey
import org.json.JSONObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ZurashiSync {

    // We now store the Firebase DB URL
    private var dbUrl: String?
        get() = getKey("firebase_db_url")
        set(value) { setKey("firebase_db_url", value) }

    private var dbSecret: String?
        get() = getKey("firebase_db_secret")
        set(value) { setKey("firebase_db_secret", value) }

    /**
     * Authenticate tests the Firebase connection by trying to read the root node.
     */
    suspend fun authenticateDevice(url: String, secret: String): Boolean {
        try {
            // Firebase REST API: append .json?auth=SECRET to the url
            val testUrl = "$url/.json?auth=$secret&shallow=true"
            val response = app.get(testUrl)

            if (response.isSuccessful) {
                dbUrl = url
                dbSecret = secret
                println("ZekaiSync: Successfully connected to Firebase!")
                return true
            }
        } catch (e: Exception) {
            println("ZekaiSync Auth Error: ${e.message}")
        }
        return false
    }

    /**
     * Pushes the timestamp using a PUT request to overwrite that specific show's data.
     */
    /**
     * Called by DataStoreHelper to ONLY update the math.
     * Uses PATCH to merge with existing metadata in Firebase.
     */
    /**
     * Called to ONLY update the math. Uses PATCH to merge with metadata.
     */
    suspend fun pushTimeMath(mediaId: String, pos: Long, dur: Long) {
        val currentUrl = dbUrl ?: return
        val currentSecret = dbSecret ?: return

        val payloadMap = mapOf(
            "media_id" to mediaId,
            "position" to pos,
            "duration" to dur,
            "last_updated" to System.currentTimeMillis()
        )

        try {
            val pushUrl = "$currentUrl/history/$mediaId.json?auth=$currentSecret"
            app.patch(pushUrl, json = payloadMap)
            android.util.Log.d("ZekaiSync", "MATH Pushed for $mediaId")
        } catch (e: Exception) { }
    }

    /**
     * Called to ONLY update visuals/links. Uses PATCH to merge with math.
     */
    suspend fun pushMetadata(mediaId: String, title: String, poster: String, videoUrl: String, apiName: String) {
        val currentUrl = dbUrl ?: return
        val currentSecret = dbSecret ?: return

        val payloadMap = mapOf(
            "title" to title,
            "poster_url" to poster,
            "video_url" to videoUrl,
            "api_name" to apiName // Push to cloud
        )

        try {
            val pushUrl = "$currentUrl/history/$mediaId.json?auth=$currentSecret"
            app.patch(pushUrl, json = payloadMap)
            android.util.Log.d("ZekaiSync", "METADATA Pushed: $title from $apiName")
        } catch (e: Exception) { }
    }
    suspend fun syncHistoryToLocal() {
        val currentUrl = dbUrl ?: return
        val currentSecret = dbSecret ?: return

        try {
            val fetchUrl = "$currentUrl/history.json?auth=$currentSecret"
            val response = app.get(fetchUrl)

            if (response.isSuccessful && response.text != "null") {
                val jsonObject = JSONObject(response.text)
                val currentAccount = DataStoreHelper.currentAccount

                val historyKey = "$currentAccount/video_history"
                // Read current history so we don't delete what they already have
                val cwList = com.lagradost.cloudstream3.CloudStreamApp.getKey<MutableList<Map<String, Any>>>(historyKey) ?: mutableListOf()
                var listChanged = false

                val keys = jsonObject.keys()
                while (keys.hasNext()) {
                    val mediaIdStr = keys.next()
                    val item = jsonObject.getJSONObject(mediaIdStr)

                    try {
                        val mediaId = item.getString("media_id").toIntOrNull() ?: continue
                        val pos = item.getLong("position")
                        val dur = item.getLong("duration")
                        val title = item.getString("title")
                        val poster = item.getString("poster_url")
                        val videoUrl = if (item.has("video_url")) item.getString("video_url") else ""
                        val apiName = if (item.has("api_name")) item.getString("api_name") else "Anichi"
                        val serverTime = item.getLong("last_updated")

                        val localData = com.lagradost.cloudstream3.CloudStreamApp.getKey<Map<String, Any>>("$currentAccount/video_pos_dur", mediaId.toString())
                        val localTime = (localData?.get("last_updated") as? Number)?.toLong() ?: 0L

                        if (localTime > serverTime && localTime != 0L) {
                            continue
                        }

                        // 1. INJECT MATH & TIMESTAMP
                        val posDurMap = mapOf("position" to pos, "duration" to dur, "last_updated" to serverTime)
                        com.lagradost.cloudstream3.CloudStreamApp.setKey("$currentAccount/video_pos_dur", mediaId.toString(), posDurMap)

                        // 2. INJECT RESUME WATCHING
                        val resumeWatchingMap = mapOf(
                            "parentId" to mediaId,
                            "episodeId" to null,
                            "episode" to 1,
                            "season" to null,
                            "updateTime" to serverTime,
                            "isFromDownload" to false
                        )
                        com.lagradost.cloudstream3.CloudStreamApp.setKey("$currentAccount/result_resume_watching_2", mediaId.toString(), resumeWatchingMap)

                        // 3. INJECT VISUALS & URL INTO CW
                        cwList.removeAll { it["id"]?.toString() == mediaId.toString() }

                        val newCard = mapOf<String, Any?>(
                            "id" to mediaId,
                            "name" to title,
                            "url" to videoUrl,
                            "apiName" to apiName,
                            "type" to "Anime",
                            "posterUrl" to poster,
                            "latestUpdatedTime" to serverTime
                        )
                        cwList.add(0, newCard as Map<String, Any>)
                        listChanged = true

                    } catch (e: Exception) {
                        android.util.Log.e("ZekaiSync", "Error parsing CW item: ${e.message}")
                    }
                }

                if (listChanged) {
                    com.lagradost.cloudstream3.CloudStreamApp.setKey(historyKey, cwList)
                    android.util.Log.d("ZekaiSync", "ZekaiSync: Firebase Smart Pull Complete! CW Injected.")
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("ZekaiSync", "Network Error during pull: ${e.message}")
        }
    }
}
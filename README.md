# Zekai

**⚠️ Warning: By default, this app doesn't provide any video sources; you have to install extensions to add functionality to the app.**

*Zekai is an unofficial, customized fork of the open-source CloudStream project, featuring a custom, decentralized cross-device sync engine.*

## 📥 Download the App
**[Download Zekai V1.0 - Launch Edition Here](https://github.com/Makurai-Zurashi/zekai/releases/tag/V1.0)**

---

## Table of Contents:
+ [About Zekai](#about_zekai)
+ [New Features](#features)
+ [Installation](#installation)
+ [How to Setup Zekai Sync](#firebase-setup)
+ [Important Legal & Copyright Note](#legal)
+ [Extensions & Upstream Support](#extensions)

<a id="about_zekai"></a>
## About Zekai:

Zekai takes the powerful, extension-based media engine of CloudStream and supercharges it with real-time cloud synchronization.

Like the original project, Zekai is a media center that prioritizes complete freedom and flexibility for users. It relies on extensions to view videos from publicly available sources, web servers, and platforms like Twitch or YouTube.

<a id="features"></a>
### Exclusive Zekai Features:
+ **Zekai Sync:** A custom-built, real-time Firebase backend that syncs your watch history, timestamps, and "Continue Watching" cards flawlessly across all your Android devices.

### Base Features (Inherited from CloudStream):
+ **AdFree**, No ads whatsoever
+ No tracking/analytics
+ Bookmarks & Watch History
+ Phone and TV support
+ Chromecast integration
+ Extension system for personal customization

<a id="installation"></a>
## Installation:

> 💡 **Recommended Tip:** Before you download and open the app, scroll down to the **Zekai Sync Setup Guide** below to generate your personal Firebase URL. Having this ready will make your initial app setup much faster!

1. Go to our [Releases Page](https://github.com/Makurai-Zurashi/zekai/releases/tag/V1.0).
2. Download the `Zekai-v1.0.apk` file to your Android device.
3. Open the file and click "Install" (you may need to allow installations from unknown sources in your Android settings).
4. *Note: Zekai's auto-updater is currently disabled to prevent accidental overwrites from the upstream CloudStream repository. Check back here for future Zekai updates!*

---

<a id="firebase-setup"></a>
## ☁️ How to Setup Zekai Sync (Cross-Device Syncing)

Zekai uses a custom-built sync engine that allows you to pause an anime on your phone and resume exactly where you left off on another device.

To keep your data 100% private and decentralized, Zekai requires you to use your own free Firebase database. It takes exactly 3 minutes to set up.

### Phase 1: Create Your Project

**Step 1:** Go to the [Firebase Console](https://console.firebase.google.com/) and sign in with your Google account. Click **Create a project**. Type in a name like `zekai-sync` and click Continue.
*(See image below)*
`[Drag and drop your Screenshot ...201240.png here]`

**Step 2:** On the next screen, turn **OFF** Google Analytics. You do not need this for a private sync engine. Click Create Project.
*(See image below)*
`[Drag and drop your Screenshot ...201343.png here]`

**Step 3:** (Optional) If it asks about AI assistance for Firebase, you can just leave it as is and hit Continue.
*(See image below)*
`[Drag and drop your Screenshot ...201330.png here]`

**Step 4:** Wait a few seconds for Firebase to provision your server. Once you see "Your Firebase project is ready", click **Continue**.
*(See image below)*
`[Drag and drop your Screenshot ...201426.png here]`

### Phase 2: Create the Database

**Step 5:** You are now on your main project dashboard. Look at the left-hand menu. Expand **Databases and storage** and click on **Realtime Database**.
*(See image below)*
`[Drag and drop your image_a2ceed.png or ...201123.png here]`

**Step 6:** Click the **Create Database** button in the center of the screen.
* Choose a location closest to you and click **Next**.
* Select **Start in Test Mode** and click **Enable**.

### Phase 3: Update Security Rules (Crucial!)
*Test mode expires after 30 days, which will break your syncing. Let's fix that so it lasts forever.*

**Step 7:** In your Realtime Database dashboard, click the **Rules** tab at the top. Change the code to look exactly like this:
```json
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
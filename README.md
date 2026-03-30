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
`<img width="1862" height="938" alt="Screenshot 2026-03-30 201240" src="https://github.com/user-attachments/assets/00fcdfdf-e6a1-47b5-a808-31cc9328f564" />`

**Step 2:** On the next screen, turn **OFF** Google Analytics. You do not need this for a private sync engine. Click Create Project.
*(See image below)*
`<img width="1856" height="942" alt="Screenshot 2026-03-30 201343" src="https://github.com/user-attachments/assets/63964abf-b70c-42b2-a42d-04e0b2fd9ced" />`

**Step 3:** (Optional) If it asks about AI assistance for Firebase, you can just leave it as is and hit Continue.
*(See image below)*
`<img width="1839" height="953" alt="Screenshot 2026-03-30 201330" src="https://github.com/user-attachments/assets/fbee59a9-8dfb-4bdd-b5a2-d28a71002fe4" />`

**Step 4:** Wait a few seconds for Firebase to provision your server. Once you see "Your Firebase project is ready", click **Continue**.
*(See image below)*
`<img width="1857" height="943" alt="Screenshot 2026-03-30 201426" src="https://github.com/user-attachments/assets/efd4b32e-e11a-450d-b558-57c2eec14bbc" />`


### Phase 2: Create the Database

**Step 5:** You are now on your main project dashboard. Look at the left-hand menu. Expand **Databases and storage** and click on **Realtime Database**.
*(See image below)*
`<img width="1862" height="943" alt="Screenshot 2026-03-30 201443" src="https://github.com/user-attachments/assets/6f9097f5-9743-454a-bbe9-3948b1932251" />`

`<img width="311" height="945" alt="Screenshot 2026-03-30 201123" src="https://github.com/user-attachments/assets/bb19351f-b883-4fb5-9a59-31c36478a823" />`

`<img width="629" height="370" alt="Screenshot 2026-03-30 201139" src="https://github.com/user-attachments/assets/2c56365f-f66b-4d48-9b22-4ac1dc6e2f62" />`

**Step 6:** Click the **Create Database** button in the center of the screen.
*(See image below)*
`<img width="1859" height="940" alt="Screenshot 2026-03-30 204042" src="https://github.com/user-attachments/assets/2c94af89-e816-48ac-9e93-cc8e4d834113" />`

* Choose a location closest to you and click **Next**.
*(See image below)*
  `<img width="1858" height="939" alt="Screenshot 2026-03-30 204105" src="https://github.com/user-attachments/assets/be480729-736d-4c3c-bcd8-3a9e39c0e170" />`

* Select **Start in Locked Mode** and click **Enable**.
*(See image below)*
`<img width="1858" height="945" alt="Screenshot 2026-03-30 204201" src="https://github.com/user-attachments/assets/b6e371e5-ad13-446e-b589-0aaed40fd26d" />`


### Phase 3: Copy the URL From Realtime database and Copy Secret Key
**Step 7: **This Page will Look like this after the Step 6**
*(See image below)*
`<img width="1852" height="946" alt="Screenshot 2026-03-30 204223" src="https://github.com/user-attachments/assets/3fc89066-9fda-4a12-8380-4217ddbc3a51" />`

**Step 8: **Copy the url next to chain icon by selecting it or clicking on chain icon And Save it somewhere**
*(See image Below)*
`<img width="578" height="75" alt="Screenshot 2026-03-30 204234" src="https://github.com/user-attachments/assets/9395004d-3db7-46bc-9e14-78029e3eaed9" />`

**Step 9: **Go to Setting -> Service accounts**
*(See image Below)*
`<img width="481" height="260" alt="Screenshot 2026-03-30 204956" src="https://github.com/user-attachments/assets/cb5e125b-fbb6-40fe-9c48-4b062b15a8dd" />`

**Step 10: **In Service accounts Go to Database secrets and click show then copy the key**
*(See image Below)*
`<img width="1861" height="930" alt="Screenshot 2026-03-30 205024" src="https://github.com/user-attachments/assets/e65ccab7-8aeb-4b06-af0e-04e34ff140d7" />`

### Phase 4: Download the App and Put Firebase Url and Secret Key
**Step 11: **Put Firebase Url and Secret Key in the pop-up which show-up when you open the app**
*(See image Below)*
`![Zekaisync](https://github.com/user-attachments/assets/8a75a4e5-1fdd-4309-953a-d35cb420d15b)`



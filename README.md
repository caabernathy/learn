# Learn Demo App

## Overview

Learn is a sample Android app to demo Facebook integration features for the Social Media Week developer event.

**Note:** Demo tested with v4.10.0 of the [Facebook SDK for Android](https://developers.facebook.com/docs/android).

## Installing

As a pre-requisite, you should have Android Studio and a working emulator or device to test with. The minimum API level supported is 15 (version 4.0.3).

1. Clone this repository
1. Open Android Studio 
1. From the welcome Quick Start, select Open an existing Android Studio project
1. Navigate to the project located in `starter/LearnApp` and open it
1. Click Run

You should see the app running in your emulator/device.

During the demo walk through, Facebook Platform features are added. To get ready for this, do the following:

1. Create a Facebook app
1. Add the Android platform to the app
1. Configure your Android settings by adding the following:
    1. Google Play Package Name: `com.facebook.samples.learn`
    1. Class Name: `com.facebook.samples.learn.MainActivity`
    1. Key Hashes: add your developer key hash
   ![](https://raw.githubusercontent.com/caabernathy/learn/master/images-docs/android_settings.png)
1. Upload the images located in the `images` directory in the App Details section
1. Add a tester for the app, it could be someone you know or a test user

Refer to the [Facebook SDK for Android](https://developers.facebook.com/android/) documentation for more information on how to set up and configure your Facebook app.

**Note:** Some of the Facebook functionality requires the native Facebook app installed and one requires the Messenger app installed. For testing these features, you can run on a device.

### Walk-through

The `walkthrough\LearnApp` project contains various steps of the demo walkthrough. You can compare the branch changes under that directory to follow along as features are added:

1. [Set up the Facebook SDK](https://github.com/caabernathy/learn/compare/setup-fb-sdk)
2. [Add Facebook Login](https://github.com/caabernathy/learn/compare/setup-fb-sdk...setup-fb-login)
3. [Personalize](https://github.com/caabernathy/learn/compare/setup-fb-login...personalize)
4. [Share on Timeline](https://github.com/caabernathy/learn/compare/personalize...add-share-timeline)
5. [Share with Messenger](https://github.com/caabernathy/learn/compare/add-share-timeline...add-share-messenger)
6. [Add the Native Like Button](https://github.com/caabernathy/learn/compare/add-share-messenger...add-like-page)
7. [Implement App Invites](https://github.com/caabernathy/learn/compare/add-like-page...add-app-invites)
8. [Add App Events](https://github.com/caabernathy/learn/compare/add-app-invites...add-app-events)

**Note:** When viewing the branch diffs, you can safely ignore the IDE-related changes found in the *.idea folder files.

### Completed app
An APK of the final app is included in the `builds` directory. You can install this to see the finished application.

To install the APK using ADB tools:

    $ cd <YOUR_ADROID_SDK_PLATFORM_TOOLS_DIRECTORY>
    $./adb install <PATH_TO_REPO_CLONE>/learn/builds/app-release.apk 

The code for the completed app is available in the repo's `completed` folder, in the `master` branch. You can also open up this code in Android Studio:

1. Open Android Studio
2. Close any currently open projects
3. From the Quick Start, select Open an existing Android Studio project
4. Navigate to the project located in `completed/LearnApp` and open it
5. Click Run

## Additional Resources

Facebook SDK for Android documentation can be found at https://developers.facebook.com/android/
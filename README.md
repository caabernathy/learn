# Learn Demo App

## Overview

Learn is a sample Android app to demo Facebook integration features for the Social Media Week developer event.

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
1. Upload the images located in the `images` directory in the App Details section
1. Add a tester for the app, it could be someone you know or a test user

Refer to the [Facebook SDK for Android](https://developers.facebook.com/android/) documentation for more information on how to set up and configure your Facebook app.

An APK of the final app is included in the `builds` directory. You can install this to see the finished application. The final code will be available after the event.

To install the APK using ADB tools:

    $ cd <YOUR_ADROID_SDK_PLATFORM_TOOLS_DIRECTORY>
    $./adb install <PATH_TO_REPO_CLONE>/learn/builds/app-release.apk 

**Note**: Some of the Facebook functionality requires the native Facebook app installed and one requires the Messenger app installed. For testing these features, you can run on a device.

## Additional Resources

Facebook SDK for Android documentation can be found at https://developers.facebook.com/android/

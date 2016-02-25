package com.facebook.samples.learn;

import android.app.Application;
import com.facebook.FacebookSdk;

/**
 * Application subclass
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}

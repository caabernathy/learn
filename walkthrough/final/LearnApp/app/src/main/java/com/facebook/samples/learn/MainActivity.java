package com.facebook.samples.learn;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import bolts.AppLinks;

/**
 * Main activity for the app
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(
                new TabFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(pager);

        // Init model info
        ModelUtils utils = ModelUtils.getInstance();

        Uri targetUrl = AppLinks.getTargetUrlFromInboundIntent(this, getIntent());
        if (targetUrl != null) {
            Log.i("Activity", "App Link Target URL: " + targetUrl.toString());
        }
    }

}

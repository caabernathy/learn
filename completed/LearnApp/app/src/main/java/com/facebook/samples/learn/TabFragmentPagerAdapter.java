package com.facebook.samples.learn;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Adapter for handling the tab display
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    static final int NUM_PAGES = 2;
    private String tabTitles[] = new String[] { "Home", "Profile" };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return ProfileFragment.newInstance();
        }
        return HomeFragment.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}

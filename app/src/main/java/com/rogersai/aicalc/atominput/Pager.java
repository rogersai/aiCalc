package com.rogersai.aicalc.atominput;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NumberAtomTab.getInstance() ;
            case 1:
                return DateAtomTab.getInstance();
            case 2:
                return MeasurementAtomTab.getInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Number";
            case 1:
                return "Date";
            case 2:
                return "Measure";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

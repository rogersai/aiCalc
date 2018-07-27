package com.rogersai.aicalc.atominput;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;

    private NumberAtomTab numberAtomTab;
    private DateAtomTab dateAtomTab;
    private MeasurementAtomTab measurementAtomTab;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.numberAtomTab = NumberAtomTab.getInstance();
        this.dateAtomTab = DateAtomTab.getInstance();
        this.measurementAtomTab = MeasurementAtomTab.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return numberAtomTab;
            case 1:
                return dateAtomTab;
            case 2:
                return measurementAtomTab;
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

package com.rogersai.aicalc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rogersai.aicalc.backend.CalcBackend;

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;
    //CalcBackend calc;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        //this.calc = CalcBackend.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NumberAtomTab nat = new NumberAtomTab();
                return nat;
            case 1:
                return new Tab2();
            case 2:
                return new Tab3();
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
                return "Dice";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

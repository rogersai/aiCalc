package com.rogersai.aicalc.atominput;

import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;

import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.backend.CalcBackend;

import java.util.ArrayList;

public class AtomBackend extends TabLayout.ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener, CloudGenerator{
    private static AtomBackend instance;
    private CalcBackend calc;
    private AtomFragment atomFragment;

    private ViewPager atomViewPager;

    public AtomBackend(ViewPager viewPager) {
        super(viewPager);
        this.atomViewPager = viewPager;
        this.calc = CalcBackend.getInstance();
        calc.setAtomBackend(this);
    }

    public static AtomBackend getInstance(ViewPager viewPager) {
       instance = new AtomBackend(viewPager);
       return instance;
    }

    public static AtomBackend getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Pair<String, String>> generateCloudItems() {
        ArrayList<Pair<String, String>> cloudItems = new ArrayList<>();
        if(atomViewPager != null) {
            cloudItems = ((CloudGenerator)((Pager)atomViewPager.getAdapter()).getItem(atomViewPager.getCurrentItem())).generateCloudItems();
        }
        return cloudItems;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        calc.repopulateCloud();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}

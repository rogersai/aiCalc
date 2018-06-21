package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.MainActivity;
import com.rogersai.aicalc.R;
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
    public ArrayList<String> generateCloudItems() {
        ArrayList<String> cloudItems = new ArrayList<>();
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

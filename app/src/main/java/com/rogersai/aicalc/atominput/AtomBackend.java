package com.rogersai.aicalc.atominput;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.backend.CalcBackend;
import com.rogersai.aicalc.symbol.atom.Atom;

import java.util.ArrayList;

public class AtomBackend extends TabLayout.ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener, CloudGenerator{
    private CalcBackend calc;
    private FragmentManager fragmentManager;


    private AtomFragment atomFragment;
    private AtomView atomView;
    private TabLayout atomTabLayout;
    private ViewPager atomViewPager;
    private Pager atomPager;

    public AtomBackend(ViewPager viewPager) {
        super(viewPager);
    }

//    private AtomBackend(ViewPager viewPager) {
//        super(viewPager);
//    }

    public static AtomBackend newInstance(ViewPager viewPager) {
        AtomBackend layout = new AtomBackend(viewPager);
        layout.calc = CalcBackend.getInstance();
        layout.atomViewPager = viewPager;
        return layout;
    }

    @Override
    public ArrayList<String> generateCloudItems() {
        ArrayList<String> cloudItems = new ArrayList<>();
        cloudItems = ((CloudGenerator)((Pager)atomViewPager.getAdapter()).getItem(atomViewPager.getCurrentItem())).generateCloudItems();
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

//    public void add(String itemText) {
//        int itemID = 0;
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        Fragment tabItem = TabLayoutItem.newInstance(itemText);
//        if(cloudItem.getArguments() != null) {
//            itemID = cloudItem.getArguments().getInt("itemID");
//        }
//        fragmentTransaction.add(tabLayoutView.getId(), cloudItem, "tab" + itemID);
//        fragmentTransaction.commit();
//
//    }

//    public void testSelf() {
//        String tag = "";
//        int itemID = 0;
//        for (int i = 0; i <=20; i++) {
//            tag = "tag" + i;
//            add(tag);
//        }
//    }
public void setAtomFragment(AtomFragment atomFragment) {
    this.atomFragment = atomFragment;
}

}

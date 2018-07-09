package com.rogersai.aicalc.cloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CloudBackend extends Fragment {
    private static CloudBackend instance;
    private FragmentManager fm;
    private CloudFragment cloudFragment;

    public static CloudBackend getInstance(CloudFragment cf) {
        if (instance == null) {
            instance = new CloudBackend();
            instance.setCloudFragment(cf);
        }
        return instance;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fm = getActivity().getSupportFragmentManager();
        testSelf();
        return null;
    }

    public static CloudBackend getInstance() {
       if (instance == null) {
           instance = new CloudBackend();
       }
       return instance;
    }

    public void addItem(String itemText, String formulaText) {
        // TODO: Implement color coding of items
        int itemID = 0;

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment cloudItem = CloudItem.newInstance(itemText, formulaText);
        if(cloudItem.getArguments() != null) {
            itemID = cloudItem.getArguments().getInt("itemID");
        }
        fragmentTransaction.add(cloudFragment.getCloudView().getId(), cloudItem, "cloud" + itemID);
        fragmentTransaction.commit();

    }
    public void clear() {
        if (cloudFragment.getCloudView()!= null) {
            cloudFragment.getCloudView().removeAllViewsInLayout();
        }
    }
    public void testSelf() {
        String tag = "";
        int itemID = 0;
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            addItem(tag, "");
        }
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public void setCloudFragment(CloudFragment cloudFragment) {
        this.cloudFragment = cloudFragment;
    }
}

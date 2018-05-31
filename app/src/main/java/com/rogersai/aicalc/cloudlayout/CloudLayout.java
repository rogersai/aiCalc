package com.rogersai.aicalc.cloudlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.R;

public class CloudLayout extends Fragment {
    private FragmentManager fragmentManager;
    private CloudLayoutView cloudLayoutView;

    public static CloudLayout newInstance() {
       CloudLayout layout = new CloudLayout();
       return layout;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cloud_layout_view, container, false);
        fragmentManager = getFragmentManager();
        cloudLayoutView = (CloudLayoutView) view;
        testSelf();
        return view;
    }

    public void addItem(String itemText) {
        int itemID = 0;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment cloudItem = CloudLayoutItem.newInstance(itemText);
        if(cloudItem.getArguments() != null) {
            itemID = cloudItem.getArguments().getInt("itemID");
        }
        fragmentTransaction.add(cloudLayoutView.getId(), cloudItem, "cloud" + itemID);
        fragmentTransaction.commit();

    }

    public void testSelf() {
        String tag = "";
        int itemID = 0;
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            addItem(tag);
        }
    }
}

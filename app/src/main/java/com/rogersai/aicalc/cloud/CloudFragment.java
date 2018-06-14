package com.rogersai.aicalc.cloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.R;

public class CloudFragment extends Fragment {
    private FragmentManager fragmentManager;
    private CloudView cloudView;

    public static CloudFragment newInstance() {
       CloudFragment layout = new CloudFragment();
       return layout;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cloud_layout_view, container, false);
        fragmentManager = getFragmentManager();
        cloudView = (CloudView) view;
        return view;
    }

    public void addItem(String itemText) {
        int itemID = 0;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment cloudItem = CloudItem.newInstance(itemText);
        if(cloudItem.getArguments() != null) {
            itemID = cloudItem.getArguments().getInt("itemID");
        }
        fragmentTransaction.add(cloudView.getId(), cloudItem, "cloud" + itemID);
        fragmentTransaction.commit();

    }

    public void clear() {
        cloudView.removeAllViewsInLayout();
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

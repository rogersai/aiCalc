package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.R;

public class AtomFragment extends Fragment {

    private ConstraintLayout atomView;
    private TabLayout atomTabLayout;
    private ViewPager atomViewPager;


    private AtomBackend backend;

    public static AtomFragment newInstance() {
       AtomFragment layout = new AtomFragment();
       return layout;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout_view, container, false);
        atomView = (ConstraintLayout) view;
        atomTabLayout = atomView.findViewById(R.id.atomTabLayout);
        atomViewPager = atomView.findViewById(R.id.atomViewPager);

        Pager adapter= new Pager(getActivity().getSupportFragmentManager(), atomTabLayout.getTabCount());
        atomViewPager.setAdapter(adapter);

        backend = AtomBackend.newInstance(atomViewPager);
        atomTabLayout.addOnTabSelectedListener(backend);
        atomTabLayout.setupWithViewPager(atomViewPager);
        return view;
    }

//    public void addItem(String itemText) {
//        int itemID = 0;
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        Fragment cloudItem = CloudItem.newInstance(itemText);
//        if(cloudItem.getArguments() != null) {
//            itemID = cloudItem.getArguments().getInt("itemID");
//        }
//        fragmentTransaction.add(atomTabLayout.getId(), cloudItem, "cloud" + itemID);
//        fragmentTransaction.commit();
//
//    }
//
//    public void testSelf() {
//        String tag = "";
//        int itemID = 0;
//        for (int i = 0; i <=20; i++) {
//            tag = "tag" + i;
//            addItem(tag);
//        }
//    }
    public AtomBackend getBackend() {
        return backend;
    }
}

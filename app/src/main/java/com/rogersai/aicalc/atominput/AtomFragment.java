package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class AtomFragment extends Fragment {
    private static AtomFragment atomFragment;

    final int[] ICONS = new int[]{
                R.drawable.number_atom_tab_icon_32,
                R.drawable.date_atom_tab_icon_32,
                R.drawable.measurement_atom_tab_icon_32
            };
    private CalcBackend calc;
    private AtomBackend atomBackend;
    private FragmentManager fm;

    private ConstraintLayout atomView;
    private TabLayout atomTabLayout;
    private ViewPager atomViewPager;

    public static AtomFragment getInstance() {
        if(atomFragment == null){
            atomFragment = new AtomFragment();
        }
        return atomFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout_view, container, false);
        atomView = (ConstraintLayout) view;
        atomTabLayout = atomView.findViewById(R.id.atomTabLayout);
        atomViewPager = atomView.findViewById(R.id.atomViewPager);

        calc = CalcBackend.getInstance();

        Pager adapter= new Pager(getActivity().getSupportFragmentManager(), atomTabLayout.getTabCount());
        atomViewPager.setAdapter(adapter);
        atomViewPager.setOffscreenPageLimit(2);
        atomBackend = AtomBackend.getInstance(atomViewPager);

        calc.setAtomBackend(atomBackend);

        return view;
    }
    public void onStart(){
        super.onStart();
        atomTabLayout.addOnTabSelectedListener(atomBackend);
        atomTabLayout.setupWithViewPager(atomViewPager);

        for(int i = 0; i < atomTabLayout.getTabCount(); i++) {
            atomTabLayout.getTabAt(i).setIcon(ICONS[i]);
        }
    }

    ///////////////////
    // Getters and Setters
    ///////////////////
    public ViewPager getAtomViewPager() {
        return atomViewPager;
    }

    public AtomBackend getAtomBackend() {
        return atomBackend;
    }
}

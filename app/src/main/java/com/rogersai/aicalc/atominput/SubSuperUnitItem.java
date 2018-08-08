package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;

public class SubSuperUnitItem extends Fragment {
    private String displayText;

    private String formulaText;
    private int itemID;
    private static int nextID = 0;

    private TextView textView;
    private TextView inputView;
    private FragmentManager fragmentManager;

    public static SubSuperUnitItem newInstance(String displayText, String formulaText) {
        SubSuperUnitItem item = new SubSuperUnitItem();
        Bundle args = new Bundle();
        args.putString("displayText", displayText);
        args.putString("formulaText", formulaText);
        args.putInt("itemID", nextID);
        item.setArguments(args);
        nextID++;
        return item;
    }

    public static SubSuperUnitItem newInstance(String displayText) {
        SubSuperUnitItem item = new SubSuperUnitItem();
        Bundle args = new Bundle();
        args.putString("displayText", displayText);
        args.putString("formulaText", displayText);
        args.putInt("itemID", nextID);
        item.setArguments(args);
        nextID++;
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_super_unit_item, container, false);

        textView = (TextView) view.findViewById(R.id.itemTextView);
        inputView = (TextView) getActivity().findViewById(R.id.inputView);

        if(getArguments() != null) {
            displayText = this.getArguments().getString("displayText");
            formulaText = this.getArguments().getString("formulaText");
        }

        textView.setText(displayText);

        fragmentManager = getFragmentManager();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MeasurementAtomTab.getInstance().getCurrentTypeFragment().getCurrentUnitView() != null) {
                    MeasurementAtomTab.getInstance().getCurrentTypeFragment().getCurrentUnitView().setSelected(false);
                }
                MeasurementAtomTab.getInstance().getCurrentTypeFragment().setCurrentUnitView(textView);
                textView.setSelected(true);
                MeasurementAtomTab.getInstance().syncWithEmptyQueue();
                MeasurementAtomTab.getInstance().queueCurrentMeasurement();
            }
        });
        return view;
    }

    public int getItemID() {
        return itemID;
    }

    public String getFormulaText() {
        return formulaText;
    }

}

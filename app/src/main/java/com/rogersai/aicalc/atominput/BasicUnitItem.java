package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.R;

public class BasicUnitItem extends Fragment{
    private String itemText;
    private String formulaText;

    private TextView textView;
    private TextView inputView;
    private UnitLister unitLister;

    public static BasicUnitItem newInstance(String text, UnitLister unitLister) {
        BasicUnitItem item = new BasicUnitItem();
        Bundle args = new Bundle();
        args.putString("itemText", text);
        item.setArguments(args);
        item.setUnitLister(unitLister);
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_unit_item, container, false);

        textView = (TextView) view.findViewById(R.id.itemTextView);
        inputView = (TextView) getActivity().findViewById(R.id.inputView);

        if(getArguments() != null) {
            itemText = this.getArguments().getString("itemText");
            formulaText = itemText;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitLister.listUnits(itemText);
                if (MeasurementAtomTab.getInstance().getCurrentUnitView() != null) {
                    MeasurementAtomTab.getInstance().getCurrentUnitView().setSelected(false);
                }
                MeasurementAtomTab.getInstance().setCurrentUnitView(textView);
                textView.setSelected(true);
                MeasurementAtomTab.getInstance().syncWithEmptyQueue();
                MeasurementAtomTab.getInstance().queueCurrentMeasurement();
            }
        });
        textView.setText(itemText);
        return view;
    }

    public void setUnitLister(UnitLister unitLister) {
        this.unitLister = unitLister;
    }

    public String getFormulaText() {
        return formulaText;
    }
}

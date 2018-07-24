package com.rogersai.aicalc.cloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class CloudItem extends Fragment {
    private String displayText;
    private CalcBackend calc;

    private String formulaText;
    private int itemID;
    private static int nextID = 0;

    private TextView textView;
    private TextView inputView;
    private FragmentManager fragmentManager;

    public static CloudItem newInstance(String displayText, String formulaText) {
        // TODO: Complete refactor for adding formulaText
        CloudItem item = new CloudItem();
        Bundle args = new Bundle();
        args.putString("displayText", displayText);
        args.putString("formulaText", formulaText);
        args.putInt("itemID", nextID);
        item.setArguments(args);
        nextID++;
        return item;
    }

    public static CloudItem newInstance(String displayText) {
        // TODO: Complete refactor for adding formulaText
        CloudItem item = new CloudItem();
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
        View view = inflater.inflate(R.layout.cloud_layout_item, container, false);

        calc = CalcBackend.getInstance();

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
                calc.input(getFormulaText());
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

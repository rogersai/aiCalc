package com.rogersai.aicalc.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class RegisterItem extends Fragment {
    private CalcBackend calc;

    private TextView formula;
    private TextView result;

    private String formulaString;

    private int itemID;
    private static int nextID = 0;

    private FragmentManager fragmentManager;

    public static RegisterItem newInstance(String text) {
        RegisterItem item = new RegisterItem();
        Bundle args = new Bundle();
        args.putString("formulaString", text);
        args.putInt("itemID", nextID);
        item.setArguments(args);
        nextID++;
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_item, container, false);

        formula = (TextView) view.findViewById(R.id.formulaView);
        result = (TextView) view.findViewById(R.id.resultView);

        if(getArguments() != null) {
            formulaString = this.getArguments().getString("formulaString");
        }

        formula.setText(formulaString);

        fragmentManager = getFragmentManager();
        formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }

    public int getItemID() {
        return itemID;
    }

    public String getFormulaString() {
        return formulaString;
    }
}

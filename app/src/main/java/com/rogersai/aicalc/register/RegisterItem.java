package com.rogersai.aicalc.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class RegisterItem extends Fragment {
    private CalcBackend calc;

    private TextView formula;
    private TextView result;
    private Button removeButton;

    private int itemID;
    private String formulaString;


    public static RegisterItem newInstance(RegisterEntry re) {
        RegisterItem item = new RegisterItem();
        Bundle args = new Bundle();
        args.putString("formulaString", re.getFormulaString());
        args.putInt("itemID", re.getId());
        item.setArguments(args);
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.register_item, container, false);

        calc = CalcBackend.getInstance();

        formula = (TextView) view.findViewById(R.id.formulaView);
        result = (TextView) view.findViewById(R.id.resultView);
        removeButton = (Button) view.findViewById(R.id.removeButton);

        if(getArguments() != null) {
            itemID = this.getArguments().getInt("itemID");
            formulaString = this.getArguments().getString("formulaString");
        }

        formula.setText(formulaString);

        formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.removeReg(itemID);
            }
        });
        return view;
    }

    public void calculate() {
        String resultText = calc.evaluate(formulaString);
        result.setText(resultText);
    }

    public int getItemID() {
        return itemID;
    }

    public String getFormulaString() {
        return formulaString;
    }
}

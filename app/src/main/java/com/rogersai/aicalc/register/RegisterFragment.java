package com.rogersai.aicalc.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class RegisterFragment extends Fragment {
    private CalcBackend calc;

    private FragmentManager fragmentManager;
    private RegisterView registerView;

    private Button buttonClear;
    private Button buttonCalc;

    public static RegisterFragment newInstance() {
       RegisterFragment layout = new RegisterFragment();
       return layout;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout_view, container, false);

        calc = CalcBackend.getInstance();
        fragmentManager = getFragmentManager();

        registerView = (RegisterView) view.findViewById(R.id.registerView);
        buttonClear = view.findViewById(R.id.buttonClearRegister);
        buttonCalc = view.findViewById(R.id.buttonCalcRegister);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.testRegister();
                calc.clear();
            }
        });
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.testRegister();
                calc.clear();
            }
        });

        return view;
    }

    public void addItem(String formulaString) {
        int itemID = 0;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment registerItem = RegisterItem.newInstance(formulaString);
        if(registerItem.getArguments() != null) {
            itemID = registerItem.getArguments().getInt("itemID");
        }
        fragmentTransaction.add(registerView.getId(), registerItem, "register" + itemID);
        fragmentTransaction.commit();

    }

    public void clear() {
        registerView.removeAllViewsInLayout();
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

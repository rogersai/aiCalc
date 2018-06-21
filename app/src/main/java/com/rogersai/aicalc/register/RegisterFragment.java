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
    private static RegisterFragment instance;
    private CalcBackend calc;
    private FragmentManager fm;
    private RegisterBackend registerBackend;


    private RegisterView registerView;

    private Button buttonClear;
    private Button buttonCalc;

    public static RegisterFragment getInstance(){
        if (instance == null) {
            instance = new RegisterFragment();
            instance.setRegisterBackend(RegisterBackend.getInstance(instance));
        }
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout_view, container, false);
        fm = getActivity().getSupportFragmentManager();

        calc = CalcBackend.getInstance();

        registerView = (RegisterView) view.findViewById(R.id.registerView);
        buttonClear = view.findViewById(R.id.buttonClearRegister);
        buttonCalc = view.findViewById(R.id.buttonCalcRegister);

        registerBackend = RegisterBackend.getInstance(this);
        FragmentTransaction ft = fm.beginTransaction();
        Fragment rb = (Fragment) registerBackend;
        ft.add(registerView.getId(), rb, "registerBackend");
        ft.commit();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.clearRegister();
            }
        });
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.getRegister().calculateAll();
            }
        });

        return view;
    }


    public RegisterView getRegisterView() {
        return registerView;
    }


    public RegisterBackend getRegisterBackend() {
        return registerBackend;
    }

    public void setRegisterBackend(RegisterBackend registerBackend) {
        this.registerBackend = registerBackend;
    }
}

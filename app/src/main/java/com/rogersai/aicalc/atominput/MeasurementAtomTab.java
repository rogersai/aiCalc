package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rogersai.aicalc.AtomTab;
import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class MeasurementAtomTab extends AtomTab implements CloudGenerator {
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private ConstraintLayout typeContainer;


    private CalcBackend calc;
    private FragmentManager fm;

    private String currentValueString = "";
    private View currentUnitView;


    private static MeasurementAtomTab instance;

    public static MeasurementAtomTab getInstance() {
        if(instance == null ) {
            instance = new MeasurementAtomTab();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.measurement_atom_tab, container, false);
        button0 = view.findViewById(R.id.button0);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);
        button7 = view.findViewById(R.id.button7);
        button8 = view.findViewById(R.id.button8);
        button9 = view.findViewById(R.id.button9);

        typeContainer = view.findViewById(R.id.typeContainer);

        calc = CalcBackend.getInstance();
        fm = getActivity().getSupportFragmentManager();

        MassTypeFragment mtf = MassTypeFragment.getInstance();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(typeContainer.getId(), mtf, "massTypeLayout");
        ft.commit();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("1");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "1";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"1");
                //NumberAtom n = (NumberAtom) evaluator.input().evaluateInput(parser.input().parseInput(inputView.getText().toString()));
                //outputView.setText(Double.toString(n.getValue()));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("2");
                //inputView.setText(inputView.getText()+"2");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "2";
                queueCurrentMeasurement();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("3");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "3";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("4");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "4";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("5");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "5";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("6");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "6";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("7");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "7";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("8");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "8";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("9");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "9";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                calc.input("0");
                syncWithEmptyQueue();
                currentValueString = currentValueString + "0";
                queueCurrentMeasurement();
                //inputView.setText(inputView.getText()+"0");
            }
        });
        return view;
    }

    public View getCurrentUnitView() {
        return currentUnitView;
    }

    public void setCurrentUnitView(View currentUnitView) {
        this.currentUnitView = currentUnitView;
    }

    public void syncWithEmptyQueue() {
        if(!calc.hasQueue()) {
            currentValueString = "";
        }

    }
    public void queueCurrentMeasurement() {
        if (!currentValueString.equals("") && currentUnitView != null) {
            calc.queue(currentValueString + ((TextView)currentUnitView).getText().toString());
        } else {
            calc.queue(currentValueString);
        }
    }

    public String getCurrentValueString() {
        return currentValueString;
    }

    public void setCurrentValueString(String currentValueString) {
        this.currentValueString = currentValueString;
    }
}

package com.rogersai.aicalc.atominput;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rogersai.aicalc.AtomTab;
import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.backend.CalcBackend;

public class MeasurementAtomTab extends AtomTab implements CloudGenerator {


    private CalcBackend calc;

    private String currentValueString = "";
    private TypeFragment currentTypeFragment;

    private MassTypeFragment mtf;
    private VolumeTypeFragment vtf;
    private LengthTypeFragment ltf;

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
        Button button0 = view.findViewById(R.id.button0);
        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);
        Button button4 = view.findViewById(R.id.button4);
        Button button5 = view.findViewById(R.id.button5);
        Button button6 = view.findViewById(R.id.button6);
        Button button7 = view.findViewById(R.id.button7);
        Button button8 = view.findViewById(R.id.button8);
        Button button9 = view.findViewById(R.id.button9);

        Button buttonMass = view.findViewById(R.id.buttonMass);
        Button buttonVolume = view.findViewById(R.id.buttonVolume);
        Button buttonLength = view.findViewById(R.id.buttonLength);

        FrameLayout typeContainer = view.findViewById(R.id.typeContainer);

        calc = CalcBackend.getInstance();
        FragmentManager fm = getActivity().getSupportFragmentManager();

        mtf = MassTypeFragment.getInstance();
        vtf = VolumeTypeFragment.getInstance();
        ltf = LengthTypeFragment.getInstance();
        setCurrentTypeFragment(mtf);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(typeContainer.getId(), mtf, "massTypeLayout");
        ft.add(typeContainer.getId(), vtf, "volumeTypeLayout");
        ft.add(typeContainer.getId(), ltf, "lengthTypeLayout");
        ft.commit();
        System.out.println("SETTING SELECTED TYPE TO MASS");


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
        buttonMass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentTypeFragment(mtf);

                mtf.getView().setVisibility(View.VISIBLE);
                vtf.getView().setVisibility(View.INVISIBLE);
                ltf.getView().setVisibility(View.INVISIBLE);

//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(typeContainer.getId(), mtf, "massTypeLayout");
//                ft.commit();
                System.out.println("SETTING SELECTED TYPE TO MASS");
            }
        });
        buttonVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentTypeFragment(vtf);

                mtf.getView().setVisibility(View.INVISIBLE);
                vtf.getView().setVisibility(View.VISIBLE);
                ltf.getView().setVisibility(View.INVISIBLE);
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(typeContainer.getId(), vtf, "volumeTypeLayout");
//                ft.commit();
                System.out.println("SETTING SELECTED TYPE TO VOLUME");
            }
        });
        buttonLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentTypeFragment(ltf);

                mtf.getView().setVisibility(View.INVISIBLE);
                vtf.getView().setVisibility(View.INVISIBLE);
                ltf.getView().setVisibility(View.VISIBLE);
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(typeContainer.getId(), ltf, "lengthTypeLayout");
//                ft.commit();
                System.out.println("SETTING SELECTED TYPE TO LENGTH");
            }
        });
        return view;
    }

    public void syncWithEmptyQueue() {
        if(!calc.hasQueue()) {
            currentValueString = "";
        }

    }
    public void queueCurrentMeasurement() {
        if (!currentValueString.equals("") && getCurrentTypeFragment().getCurrentUnitView() != null) {
            calc.queue(currentValueString + ((TextView)getCurrentTypeFragment().getCurrentUnitView()).getText().toString());
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

    public TypeFragment getCurrentTypeFragment() {
        return currentTypeFragment;
    }

    public void setCurrentTypeFragment(TypeFragment currentTypeFragment) {
        this.currentTypeFragment = currentTypeFragment;
    }
}

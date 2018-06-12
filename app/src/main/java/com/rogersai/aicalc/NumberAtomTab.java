package com.rogersai.aicalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rogersai.aicalc.backend.CalcBackend;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class NumberAtomTab extends Fragment {
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonLParen, buttonRParen;
    private CalcBackend calc;
//    private TextView inputView, outputView;
//    private Parser parser;
//    private Evaluator evaluator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_atom_tab, container, false) ;
        button0 = (Button) view.findViewById(R.id.button0);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        buttonLParen = (Button) view.findViewById(R.id.buttonLParen);
        buttonRParen = (Button) view.findViewById(R.id.buttonRParen);

        calc = ((MainActivity)getActivity()).getCalc();

//        inputView = (TextView) getActivity().findViewById(R.id.inputView);
//        outputView = (TextView) getActivity().findViewById(R.id.outputView);
//
//        parser = DaggerParser.create();
//        evaluator = DaggerEvaluator.create();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("1");
                //inputView.setText(inputView.getText()+"1");
                //NumberAtom n = (NumberAtom) evaluator.input().evaluate(parser.input().parse(inputView.getText().toString()));
                //outputView.setText(Double.toString(n.getValue()));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("2");
                //inputView.setText(inputView.getText()+"2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("3");
                //inputView.setText(inputView.getText()+"3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("4");
                //inputView.setText(inputView.getText()+"4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("5");
                //inputView.setText(inputView.getText()+"5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("6");
                //inputView.setText(inputView.getText()+"6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("7");
                //inputView.setText(inputView.getText()+"7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("8");
                //inputView.setText(inputView.getText()+"8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("9");
                //inputView.setText(inputView.getText()+"9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("0");
                //inputView.setText(inputView.getText()+"0");
            }
        });
        buttonLParen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input("(");
                //inputView.setText(inputView.getText()+"(");
            }
        });
        buttonRParen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.input(")");
                //inputView.setText(inputView.getText()+")");
            }
        });

        return view;
    }
}

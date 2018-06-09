package com.rogersai.aicalc.backend;

import android.app.Fragment;
import android.widget.TextView;

import com.rogersai.aicalc.DaggerEvaluator;
import com.rogersai.aicalc.DaggerParser;
import com.rogersai.aicalc.Evaluator;
import com.rogersai.aicalc.MainActivity;
import com.rogersai.aicalc.Parser;
import com.rogersai.aicalc.R;

import javax.inject.Singleton;

@Singleton
public class CalcBackend extends Fragment{
    private Parser parser;


    private Evaluator evaluator;
    private TextView input;
    private TextView output;

    public static CalcBackend newInstance(MainActivity mainActivity) {
        CalcBackend calc = new CalcBackend();
        calc.setParser(DaggerParser.create());
        calc.setEvaluator(DaggerEvaluator.create());
        calc.setInput((TextView) mainActivity.findViewById(R.id.inputView));
        calc.setOutput((TextView) mainActivity.findViewById(R.id.outputView));
        return calc;
    }
    public Parser parser() {
        return parser;
    }
    public Evaluator evaluator() {
        return  evaluator;
    }
    public TextView input(){
        return input;
    }
    public TextView output(){
        return output;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void setInput(TextView input) {
        this.input = input;
    }

    public void setOutput(TextView output) {
        this.output = output;
    }
}

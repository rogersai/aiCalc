package com.rogersai.aicalc.backend;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.rogersai.aicalc.DaggerEvaluator;
import com.rogersai.aicalc.DaggerParser;
import com.rogersai.aicalc.Evaluator;
import com.rogersai.aicalc.MainActivity;
import com.rogersai.aicalc.Parser;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.cloudlayout.CloudLayout;
import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import java.util.ArrayList;

import javax.inject.Singleton;

@Singleton
public class CalcBackend extends Fragment{
    private static CalcBackend calc;

    private FragmentManager fm;

    private Parser parser;
    private Evaluator evaluator;

    private TextView input;
    private TextView output;

    private CloudLayout cloud;

    private boolean hasNumberAtom;

    private final String operators = "+-x/";
    private final String groupers = "()";

    private ArrayList<Symbol> parsedList;

    public static CalcBackend getInstance(MainActivity mainActivity) {
        if(calc== null) {
            calc = new CalcBackend();

            calc.setFm(mainActivity.getSupportFragmentManager());

            calc.setParser(DaggerParser.create());
            calc.setEvaluator(DaggerEvaluator.create());

            calc.setInput((TextView) mainActivity.findViewById(R.id.inputView));
            calc.setOutput((TextView) mainActivity.findViewById(R.id.outputView));

            ConstraintLayout cloudContainer = (ConstraintLayout) mainActivity.findViewById(R.id.cloudContainer);
            calc.setCloud(CloudLayout.newInstance());

            calc.hasNumberAtom = false;
            calc.parsedList = new ArrayList<>();

            FragmentTransaction ft = calc.fm.beginTransaction();
            Fragment cl = (Fragment) calc.cloud;
            ft.add(cloudContainer.getId(), cl, "cloudLayout");
            ft.commit();

        }
        return calc;
    }

    public static CalcBackend getInstance(){
        return calc;
    }


    ///////////////
    // Main Methods
    ///////////////
    public void input(String s) {
        if(!input.getText().equals("") && operators.contains(s) && getLastCategory().equals("operator")) {
            input.setText(input.getText().subSequence(0, input.getText().length() - 1));
        }
        input.setText(input.getText() + s);
        parse();
        if(getLastCategory().equals("atom")){
            evaluate();
        }
    }
    public void clear() {
        input.setText("");
        clearSymbols();
    }
    public void parse() {
        parsedList = parser.input().parse(input.getText().toString());
    }
    public void evaluate() {
        NumberAtom n = (NumberAtom) evaluator.input().evaluate(parser.input().parse(input.getText().toString()));
        output.setText(Double.toString(n.getValue()));
    }
    public String reportInput() {
        return input.getText().toString();
    }
    public void cloud(String s) {
        cloud.addItem(s);
    }
    public void clearSymbols() {
        this.hasNumberAtom = false;
    }
    private String getLastCategory() {
        if(!parsedList.isEmpty()) {
            return parsedList.get(parsedList.size() - 1).getCategory();
        } else {
            return "";
        }
    }
    public void testCloud() {
        String tag = "";
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            cloud(tag);
        }
    }
    //////////////////////
    // Getters and Setters
    //////////////////////
    public void setHasNumberAtom(boolean hasNumberAtom) {
        this.hasNumberAtom = hasNumberAtom;
    }
    private void setParser(Parser parser) {
        this.parser = parser;
    }

    private void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    private void setInput(TextView input) {
        this.input = input;
    }

    private void setOutput(TextView output) {
        this.output = output;
    }
    private void setCloud(CloudLayout cloudLayout) {
        this.cloud= cloudLayout;
    }
    private void setFm(FragmentManager fm) {
        this.fm = fm;
    }
}

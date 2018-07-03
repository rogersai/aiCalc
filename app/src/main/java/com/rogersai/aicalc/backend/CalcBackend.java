package com.rogersai.aicalc.backend;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.widget.TextView;

import com.rogersai.aicalc.CalendarContentRetriever;
import com.rogersai.aicalc.DaggerEvaluator;
import com.rogersai.aicalc.DaggerParser;
import com.rogersai.aicalc.Evaluator;
import com.rogersai.aicalc.MainActivity;
import com.rogersai.aicalc.Parser;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.atominput.AtomBackend;
import com.rogersai.aicalc.atominput.AtomFragment;
import com.rogersai.aicalc.cloud.CloudBackend;
import com.rogersai.aicalc.register.RegisterBackend;
import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;

import java.util.ArrayList;

import javax.inject.Singleton;

@Singleton
public class CalcBackend extends Fragment{
    private static CalcBackend calc;


    private FragmentManager fm;

    private AtomBackend tabs;
    private CloudBackend cloud;
    private RegisterBackend register;

    private AtomFragment atomFragment;

    private Parser parser;
    private Evaluator evaluator;

    private TextView input;
    private TextView output;
    private Atom outputAtom;

    //private CloudFragment cloud;
    //private AtomFragment tabs;

//    private RegisterFragment register;

    private boolean hasNumberAtom;

    private final String operators = "+-x/";
    private final String groupers = "()";

    private ArrayList<Symbol> parsedList;

    public static CalcBackend newInstance(final MainActivity mainActivity) {
        if(calc== null) {
            calc = new CalcBackend();

            calc.setFm(mainActivity.getSupportFragmentManager());

            CalendarContentRetriever ccr = new CalendarContentRetriever(mainActivity);
            ccr.testSelf();


            //calc.setAtomBackend(AtomBackend.newInstance(mainActivity));
//            calc.setCloud(CloudBackend.newInstance());
//            calc.setRegister(RegisterBackend.newInstance(mainActivity));
//
            calc.setParser(DaggerParser.create());
            calc.setEvaluator(DaggerEvaluator.create());

            calc.setInput((TextView) mainActivity.findViewById(R.id.inputView));
            calc.setOutput((TextView) mainActivity.findViewById(R.id.outputView));

            calc.hasNumberAtom = false;
            calc.parsedList = new ArrayList<>();
        }
        return calc;
    }

    public static CalcBackend getInstance(){
        return calc;
    }



    //////////////////////
    // Processing Functions
    //////////////////////
    public ArrayList<Symbol> parse(String s){
        parsedList = parser.input().parse(s);
        return parsedList;
    }

    public Symbol evaluate(String s) {
        Symbol result =  evaluator.input().evaluate(parser.input().parse(s));
        return result;
    }

    //////////////////////
    // Display Functions
    //////////////////////
    public void input(String s) {
        if(!input.getText().equals("") && operators.contains(s) && getLastCategory().equals("operator")) {
            input.setText(input.getText().subSequence(0, input.getText().length() - 1));
        }
        input.setText(input.getText() + s);
        parseInput();
        if(getLastCategory().equals("atom")){
            evaluateInput();
        }
    }

    public void parseInput() {
        parsedList = parse(input.getText().toString());
    }

    public void evaluateInput() {
        Symbol s = evaluate(input.getText().toString());
        //NumberAtom n = (NumberAtom) evaluator.input().evaluate(parser.input().parseInput(input.getText().toString()));
        output.setText(s.toString());
    }

    public void clear() {
        input.setText("");
        clearSymbols();
    }

    //////////////////////
    // Cloud Functions
    //////////////////////
    public void cloud(ArrayList<Pair<String, String>> cloudList) {
        if(cloudList != null && cloudList.size() > 0) {
            for (int i = 0; i < cloudList.size(); i++) {
                cloud.addItem(cloudList.get(i).first, cloudList.get(i).second);
            }
        }
    }

    public void clearCloud() {
            cloud.clear();
    }

    public void inputToCloud() {

    }

    public void outputToCloud() {
        cloud(parser.atom().parse(output.getText().toString()).generateCloudItems());
    }
    public void inputTabsToCloud() {
        if(tabs != null) {
            cloud(tabs.generateCloudItems());
        }
    }

    public void repopulateCloud() {
//        try {
            clearCloud();
        inputTabsToCloud();
            // TODO: Input Cloud
            outputToCloud();
//        } catch (NullPointerException e) {
//            System.out.println("Exception while repopulating cloud: " + e.getMessage());
//        }
    }
    public void testCloud() {
        ArrayList<Pair<String, String>> cloudList = new ArrayList<>();
        String tag = "";
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            cloudList.add(new Pair(tag, tag));
        }
        cloud(cloudList);
    }

    //////////////////////
    // Register Functions
    //////////////////////
    public void register(ArrayList<String> registerList) {
        for (String s: registerList) {
            register.add(s);
        }
    }

    public void registerInput() {
        String formulaString = input.getText().toString();
        register.add(formulaString);
    }
    public void clearRegister() {
            register.clear();
    }

    public void testRegister() {
        ArrayList<String> registerList = new ArrayList<>();
        String tag = "";
        for (int i = 0; i <=20; i++) {
            tag = "tag" + i;
            registerList.add(tag);
        }
        register(registerList);
    }
    public void removeReg(int id) {
        register.remove(id);
    }
    ///////////////
    // Support Methods
    ///////////////
    public String reportInput() {
        return input.getText().toString();
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
    //////////////////////
    // Getters and Setters
    //////////////////////
    public RegisterBackend getRegister() {
        return register;
    }

    public void setAtomBackend(AtomBackend atomBackend) {
        this.tabs = atomBackend;
    }
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

    private void setTabs(AtomBackend tabs) {
        this.tabs = tabs;
    }

    private void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public void setCloud(CloudBackend cloudBackend) {
        this.cloud = cloudBackend;
    }

    public void setRegister(RegisterBackend register) {
        this.register = register;
    }
}

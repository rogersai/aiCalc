package com.rogersai.aicalc.backend;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.widget.TextView;

import com.rogersai.aicalc.CalendarContentRetriever;
import com.rogersai.aicalc.ContactContentRetriever;
import com.rogersai.aicalc.DaggerEvaluator;
import com.rogersai.aicalc.DaggerParser;
import com.rogersai.aicalc.Evaluator;
import com.rogersai.aicalc.MainActivity;
import com.rogersai.aicalc.Parser;
import com.rogersai.aicalc.R;
import com.rogersai.aicalc.atominput.AtomBackend;
import com.rogersai.aicalc.atominput.AtomFragment;
import com.rogersai.aicalc.backend.evaluator.Caster;
import com.rogersai.aicalc.cloud.CloudBackend;
import com.rogersai.aicalc.register.RegisterBackend;
import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class CalcBackend extends Fragment{
    private static CalcBackend calc;


    private FragmentManager fm;


    private CalendarContentRetriever calendarCR;
    private ContactContentRetriever contactCR;

    private AtomBackend tabs;
    private CloudBackend cloud;
    private RegisterBackend register;

    private AtomFragment atomFragment;

    private Parser parser;
    private Evaluator evaluator;
    private Caster caster;

    private TextView input;
    private TextView output;
    private Atom outputAtom;

    private String inputFormula = "";
    private String inputDisplay = "";

    private String currentInputFormula = "";
    private String queuedInputFormula = "";

    private String currentInputDisplay = "";
    private String queuedInputDisplay = "";

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

            calc.setCalendarCR(new CalendarContentRetriever(mainActivity));
            calc.setContactCR(new ContactContentRetriever(mainActivity));
            //calc.getContactCR().testSelf();

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
        Symbol result =  evaluator.input().evaluate(parse(s));
        return result;
    }

    public Atom[] cast(Atom[] operands) {
        Atom[] result = evaluator.caster().cast(operands);
        return result;
    }

    //////////////////////
    // Display Functions
    //////////////////////
    public void input(String formula, String display) {
        if (!queuedInputFormula.equals("")) {
            inputFormula = currentInputFormula + queuedInputFormula;
            inputDisplay = currentInputDisplay + queuedInputDisplay;
            input.setText(inputDisplay);
        }
        currentInputFormula = "";
        queuedInputFormula = "";
        currentInputDisplay = "";
        queuedInputDisplay = "";
        if(!inputFormula.equals("") && operators.contains(formula) && getLastCategory().equals("operator")) {
            inputFormula = String.valueOf(inputFormula.subSequence(0, inputFormula.length() - 1));
            inputDisplay = String.valueOf(inputDisplay.subSequence(0, inputDisplay.length() - 1));
            input.setText(inputDisplay);
        }
        inputFormula = inputFormula + formula;
        inputDisplay = inputDisplay + display;
        input.setText(inputDisplay);
        parseInput();
        if(getLastCategory().equals("atom")){
            evaluateInput();
        }
    }

    public void input(String formula) {
        input(formula, formula);
    }

    public void queue(String formula, String display) {
        if (queuedInputFormula.equals("")) {
            if (!inputFormula.equals("null")) {
                currentInputFormula = inputFormula;
                currentInputDisplay = inputDisplay;
            } else {
                currentInputFormula = "";
                currentInputDisplay = "";
            }
        }
        queuedInputFormula = formula;
        queuedInputDisplay = display;
        inputFormula = currentInputFormula + queuedInputFormula;
        inputDisplay = currentInputDisplay + queuedInputDisplay;
        input.setText(inputDisplay);
        parseInput();
        if(getLastCategory().equals("atom")){
            evaluateInput();
        }
    }

    public void queue(String formula) {
        queue(formula, formula);
    }

    public boolean hasQueue() {
        if (!queuedInputFormula.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public void parseInput() {
        parsedList = parse(inputFormula);
    }

    public void evaluateInput() {
        Symbol s = evaluate(inputFormula);
        output.setText(s.toString());
        repopulateCloud();
    }

    public void clear() {
        input.setText("");
        inputFormula = "";
        inputDisplay = "";
        parsedList.clear();
        currentInputFormula = "";
        queuedInputFormula = "";
        currentInputDisplay = "";
        queuedInputDisplay = "";
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
        register.add(inputFormula);
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


    //////////////////////
    // Content Methods
    //////////////////////
    public ArrayList<Pair<String, String>> getHolidays() {
        return calendarCR.getHolidays();
    }
    public ArrayList<Pair<String, String>> getBirthdays() {
        Map<String, String> birthdays =  contactCR.getBirthdays();
        ArrayList<Pair<String, String>> birthdayList = new ArrayList<>();
        for(String name : birthdays.keySet()){
            String displayText = name.concat("'s Birthday");
            DateAtom atom = new DateAtom(birthdays.get(name));
            String formulaText = atom.toString();
            Pair<String, String> pair = new Pair<>(displayText, formulaText);
            birthdayList.add(pair);
        }
        return birthdayList;
    }

    public void removeReg(int id) {
        register.remove(id);
    }
    ///////////////
    // Support Methods
    ///////////////
    public String reportInput() {
        return inputFormula;
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
    public CalendarContentRetriever getCalendarCR() {
        return calendarCR;
    }

    public void setCalendarCR(CalendarContentRetriever calendarCR) {
        this.calendarCR = calendarCR;
    }

    public ContactContentRetriever getContactCR() {
        return contactCR;
    }

    public void setContactCR(ContactContentRetriever contactCR) {
        this.contactCR = contactCR;
    }

    public void setCaster(Caster caster) {
        this.caster = caster;
    }
}

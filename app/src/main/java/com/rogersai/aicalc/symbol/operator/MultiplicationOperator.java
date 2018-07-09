package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class MultiplicationOperator extends Operator {

    public MultiplicationOperator() {
        super();
        category = "operator";
        type = "multiplication";
    }

//    @Override
//    public Atom operate(Atom pre, Atom post) {
//        Atom result = null;
//        if (pre.getType().equals("number") || post.getType().equals("number")) {
//            result =  operate(pre.toNumber(), post.toNumber());
//        } else {
//            System.out.println("Multiplication failed, falling back to blank result");
//            result =  new Atom();
//        }
//        return result;
//    }

    public NumberAtom operate(NumberAtom pre, NumberAtom post) {
        return new NumberAtom(pre.getValue() * post.getValue());
    }

    @Override
    public DateAtom operate(DateAtom pre, DateAtom post) {
        //TODO: Implement multiplication of dates (currently mimics addition)
        System.out.println("Pre: " + pre.getDate().toString());
        System.out.println("Post: " + post.getDate().toString());
        System.out.println("Epoch: " + DateAtom.EPOCH.toString());
//        System.out.println("Epoch2: " + DateAtom.EPOCH2.toString());
//        System.out.println("Epoch3: " + DateAtom.EPOCH3.toString());
        Duration postDuration = new Duration(DateAtom.EPOCH, post.getDate());
        System.out.println("Post duration: " + postDuration.getStandardDays());
        DateTime result = pre.getDate().withDurationAdded(postDuration, 1);
        System.out.println("Added: " + result.toString());
//        DateTime result = new DateTime(pre.getDate().getMillis() + post.getDate().getMillis(), DateTimeZone.UTC);
        return new DateAtom(result);
    }
    @Override
    public MeasurementAtom operate(MeasurementAtom pre, MeasurementAtom post) {
        // TODO: Fix implementation for different units
        double resultValue = pre.getValue() + post.getValue();
        String resultUnit = pre.getUnit();
        return new MeasurementAtom(resultValue, resultUnit);
    }

    @Override
    public String toString() {
        return "x";
    }
}

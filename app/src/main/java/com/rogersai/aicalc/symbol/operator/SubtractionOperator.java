package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class SubtractionOperator extends Operator {
    public SubtractionOperator() {
        super();
        category = "operator";
        type = "subtraction";
    }

//    @Override
//    public Atom operate(Atom pre, Atom post) {
//        Atom result = null;
//        if (pre.getType().equals("number") || post.getType().equals("number")) {
//            result =  operate(pre.toNumber(), post.toNumber());
//        } else {
//            System.out.println("Subtraction failed, falling back to blank result");
//            result =  new Atom();
//        }
//        return result;
//    }

    public NumberAtom operate(NumberAtom pre, NumberAtom post) {
        return new NumberAtom(pre.getValue() - post.getValue());
    }

    @Override
    public DateAtom operate(DateAtom pre, DateAtom post) {
//        System.out.println("Pre: " + pre.getDate().toString());
//        System.out.println("Post: " + post.getDate().toString());
//        System.out.println("Epoch: " + DateAtom.EPOCH.toString());
//        System.out.println("Epoch2: " + DateAtom.EPOCH2.toString());
//        System.out.println("Epoch3: " + DateAtom.EPOCH3.toString());
        Duration difference = new Duration(post.getDate(), pre.getDate());
//        System.out.println("Post duration: " + postDuration.getStandardDays());
        DateTime result = DateAtom.EPOCH.withDurationAdded(difference, 1);
//        System.out.println("Added: " + result.toString());
//        DateTime result = new DateTime(pre.getDate().getMillis() + post.getDate().getMillis(), DateTimeZone.UTC);
        return new DateAtom(result);
    }

    @Override
    public String toString() {
        return "-";
    }
}

package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class Operator extends Symbol {

    public Operator() {
        category = "operator";
    }

//    public Atom operate(Atom pre, Atom post) {
//        Atom result = null;
//        if (pre.getType().equals("number") && post.getType().equals("number")) {
//            result =  operate(pre.toNumber(), post.toNumber());
//        } else if (pre.getType().equals("date") && post.getType().equals("date")) {
//            result =  operate(pre.toDate(), post.toDate());
//        } else {
//            result =  new Atom();
//        }
//        return result;
//    }

    public Atom operate(Atom[] operands) {
        Atom pre = operands[0];
        Atom post = operands[1];
        switch (pre.getType()){
            case "number":
                return operate((NumberAtom) pre, (NumberAtom) post);
            case "date":
                return operate((DateAtom) pre, (DateAtom) post);
            case "measurement":
                return operate((MeasurementAtom) pre, (MeasurementAtom) post);
            default:
                System.out.println("Operation failed, falling back to blank result");
                return new Atom();
        }
//        if(operands[0].getType().equals("number")) {
//            return operate((NumberAtom) pre, (NumberAtom) post);
//        } else if(operands[0].getType().equals("date")) {
//            return operate((DateAtom) pre, (DateAtom) post);
//        } else if(operands[0].getType().equals("measurement")) {
//            return operate((MeasurementAtom) pre, (MeasurementAtom) post);
//        } else{
//            System.out.println("Addition failed, falling back to blank result");
//            return new Atom();
//        }
    }

    public NumberAtom operate(NumberAtom pre, NumberAtom post) {
        System.out.println("Operated in superclass");
        return new NumberAtom(0);
    }

    public DateAtom operate(DateAtom pre, DateAtom post) {
        System.out.println("Operated in superclass");
        return new DateAtom(0,0,0);
    }

    public MeasurementAtom operate(MeasurementAtom pre, MeasurementAtom post) {
        System.out.println("Operated in superclass");
        return new MeasurementAtom(0,"");
    }
}

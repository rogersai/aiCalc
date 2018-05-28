package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;
import com.rogersai.aicalc.symbol.operator.DivisionOperator;
import com.rogersai.aicalc.symbol.operator.MultiplicationOperator;
import com.rogersai.aicalc.symbol.operator.Operator;
import com.rogersai.aicalc.symbol.operator.SubtractionOperator;

import javax.inject.Inject;

public class OperatorParser {
    public Operator parse(String input){
        if (input.equals("+")) {
            return new AdditionOperator();
        } else if (input.equals("-")) {
            return new SubtractionOperator();
        } else if (input.equals("x")) {
            return new MultiplicationOperator();
        } else if (input.equals("/")) {
            return new DivisionOperator();
        } else {
            return new Operator();
        }
    }
}

package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class AdditionOperator extends Operator {

    public AdditionOperator() {
        super();
        category = "operator";
        type = "addition";
    }

    @Override
    public Symbol operate(Symbol pre, Symbol post) {
        Symbol result = null;
        if (pre.getType().equals("number") || post.getType().equals("number")) {
            result =  operate(pre.toNumber(), post.toNumber());
        } else {
            System.out.println("Addition failed, falling back to blank result");
            result =  new Symbol();
        }
        return result;
    }

    public NumberAtom operate(NumberAtom pre, NumberAtom post) {
        return new NumberAtom(pre.getValue() + post.getValue());
    }

    @Override
    public String toString() {
        return "+";
    }
}

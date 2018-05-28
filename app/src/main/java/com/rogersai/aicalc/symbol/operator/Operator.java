package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class Operator extends Symbol {

    public Operator() {
        category = "operator";
    }

    public Symbol operate(Symbol pre, Symbol post) {
        return new Symbol();
    }
}

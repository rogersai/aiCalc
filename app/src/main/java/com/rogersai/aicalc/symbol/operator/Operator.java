package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class Operator extends Symbol {

    public Operator() {
        category = "operator";
    }

    public Symbol operate(Atom pre, Atom post) {
        return new Atom();
    }
}

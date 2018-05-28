package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import javax.inject.Inject;

public class AtomParser {
    public Atom parse(String input){
        Atom atom = new Atom();
        double value = 0;
        try {
            value = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        atom  = new NumberAtom(value);
        return atom;
    }
}

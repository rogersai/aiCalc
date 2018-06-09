package com.rogersai.aicalc.backend.parser;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

public class AtomParser {
    public Atom parse(String input){
        double value = 0;
        try {
            value = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        Atom atom  = new NumberAtom(value);
        return atom;
    }
}

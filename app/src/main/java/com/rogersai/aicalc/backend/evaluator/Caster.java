package com.rogersai.aicalc.backend.evaluator;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.joda.time.DateTime;

public class Caster {
    public Atom[] cast(NumberAtom n1, NumberAtom n2) {
        return new Atom[] {n1, n2};
    }
    public Atom[] cast(DateAtom d1, DateAtom d2) {
        return new Atom[] {d1, d2};
    }
    public Atom[] cast(NumberAtom n, DateAtom d) {
        DateTime numberDate = DateAtom.EPOCH.plusDays((int)Math.round(n.getValue()));
        DateAtom numberDateAtom = new DateAtom(numberDate);
        return new Atom[] {numberDateAtom, d};
    }
    public Atom[] cast(DateAtom d, NumberAtom n) {
        DateTime numberDate = DateAtom.EPOCH.plusDays((int)Math.round(n.getValue()));
        DateAtom numberDateAtom = new DateAtom(numberDate);
        return new Atom[] {d, numberDateAtom};
    }
}

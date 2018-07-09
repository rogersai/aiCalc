package com.rogersai.aicalc.backend.evaluator;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.joda.time.DateTime;

public class Caster {
    public Atom[] cast(NumberAtom n1, NumberAtom n2) {
        return new Atom[] {n1, n2};
    }
    public Atom[] cast(DateAtom d1, DateAtom d2) {
        return new Atom[] {d1, d2};
    }
    public Atom[] cast(MeasurementAtom d1, MeasurementAtom d2) {
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
    public Atom[] cast(MeasurementAtom m, NumberAtom n) {
        MeasurementAtom measuredNumber = new MeasurementAtom(n.getValue(), m.getUnit());
        return new Atom[] {m, measuredNumber};
    }
    public Atom[] cast(NumberAtom n, MeasurementAtom m) {
        MeasurementAtom measuredNumber = new MeasurementAtom(n.getValue(), m.getUnit());
        return new Atom[] {measuredNumber, m};
    }
    public Atom[] cast(MeasurementAtom m, DateAtom d) {
        // TODO: Add fix to properly handle units of time
        NumberAtom numberedMeasure = new NumberAtom(m.getValue());
        return cast(numberedMeasure, d);
    }
    public Atom[] cast(DateAtom d, MeasurementAtom m) {
        // TODO: Add fix to properly handle units of time
        NumberAtom numberedMeasure = new NumberAtom(m.getValue());
        return cast(d, numberedMeasure);
    }
}

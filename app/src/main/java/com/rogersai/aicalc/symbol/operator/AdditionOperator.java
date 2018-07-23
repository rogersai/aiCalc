package com.rogersai.aicalc.symbol.operator;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.util.Date;

public class AdditionOperator extends Operator {

    public AdditionOperator() {
        super();
        category = "operator";
        type = "addition";
    }

    @Override
    public NumberAtom operate(NumberAtom pre, NumberAtom post) {
        return new NumberAtom(pre.getValue() + post.getValue());
    }

    @Override
    public DateAtom operate(DateAtom pre, DateAtom post) {
        Duration postDuration = new Duration(DateAtom.EPOCH, post.getDate());
        System.out.println("Post duration: " + postDuration.getStandardDays());
        DateTime result = pre.getDate().withDurationAdded(postDuration, 1);
        System.out.println("Added: " + result.toString());
        return new DateAtom(result);
    }

    @Override
    public MeasurementAtom operate(MeasurementAtom pre, MeasurementAtom post) {
        double resultValue = 0;
        if (pre.getUnit().equals(post.getUnit())) {
            resultValue = pre.getValue() + post.getValue();
        } else {
            MeasurementAtom converted = MeasurementAtom.convert(post, pre.getUnit());
            resultValue = pre.getValue() + converted.getValue();
        }
        return new MeasurementAtom(resultValue, pre.getUnit());
    }

    @Override
    public String toString() {
        return "+";
    }

}

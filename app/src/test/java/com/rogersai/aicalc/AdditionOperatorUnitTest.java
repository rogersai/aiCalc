package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.evaluator.Caster;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AdditionOperatorUnitTest {

    @Test
    public void datePlusDate() {
        //System.out.println(DateAtom.EPOCH.toString());
        AdditionOperator op = new AdditionOperator();
        DateAtom date1 = new DateAtom(10, 1, 1970);
        DateAtom date2 = new DateAtom(12, 1, 1970);
        DateAtom expected = new DateAtom(21, 1, 1970);
        System.out.println("Expected: " + expected.getDate().toString());
        DateAtom result = op.operate(date1, date2);
        System.out.println("Result: " + result.getDate().toString());
        assertEquals(expected, result);
    }

    @Test
    public void datePlusDateAsAtoms() {
        //System.out.println(DateAtom.EPOCH.toString());
        AdditionOperator op = new AdditionOperator();
        Atom date1 = (Atom) new DateAtom(10, 1, 1970);
        Atom date2 = (Atom) new DateAtom(12, 1, 1970);
        DateAtom expected = new DateAtom(21, 1, 1970);
        System.out.println("Expected: " + expected.getDate().toString());
        Atom result = op.operate(date1, date2);
        System.out.println("Result: " + ((DateAtom)result).getDate().toString());
        assertEquals(expected, result);
    }

    @Test
    public void datePlusDateReversed() {
        //System.out.println(DateAtom.EPOCH.toString());
        AdditionOperator op = new AdditionOperator();
        DateAtom date1 = new DateAtom(10, 1, 1970);
        DateAtom date2 = new DateAtom(12, 1, 1970);
        DateAtom expected = new DateAtom(21, 1, 1970);
        System.out.println("Expected: " + expected.getDate().toString());
        DateAtom result = op.operate(date2, date1);
        System.out.println("Result: " + result.getDate().toString());
        assertEquals(expected, result);
    }

    @Test
    public void numberPlusDate() {
        //System.out.println(DateAtom.EPOCH.toString());
        AdditionOperator op = new AdditionOperator();
        Caster caster = new Caster();
        NumberAtom n = new NumberAtom(10);
        DateAtom d = new DateAtom(10, 1, 1970);
        DateAtom expected = new DateAtom(20, 1, 1970);
        System.out.println("Expected: " + expected.getDate().toString());
        Atom[] castInputs = caster.cast(n, d);
        DateAtom result = op.operate((DateAtom)castInputs[0], (DateAtom)castInputs[1]);
        System.out.println("Result: " + result.getDate().toString());
        assertEquals(expected, result);
    }

    @Test
    public void datePlusNumber() {
        //System.out.println(DateAtom.EPOCH.toString());
        AdditionOperator op = new AdditionOperator();
        Caster caster = new Caster();
        DateAtom d = new DateAtom(10, 1, 1970);
        NumberAtom n = new NumberAtom(10);
        DateAtom expected = new DateAtom(20, 1, 1970);
        System.out.println("Expected: " + expected.getDate().toString());
        Atom[] castInputs = caster.cast(d, n);
        DateAtom result = op.operate((DateAtom)castInputs[0], (DateAtom)castInputs[1]);
        System.out.println("Result: " + result.getDate().toString());
        assertEquals(expected, result);
    }

}
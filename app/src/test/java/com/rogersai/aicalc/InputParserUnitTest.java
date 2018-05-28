package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;
import com.rogersai.aicalc.symbol.grouper.LParenGrouper;
import com.rogersai.aicalc.symbol.grouper.RParenGrouper;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;
import com.rogersai.aicalc.symbol.operator.DivisionOperator;
import com.rogersai.aicalc.symbol.operator.MultiplicationOperator;
import com.rogersai.aicalc.symbol.operator.SubtractionOperator;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InputParserUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parseOneNumber() {
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("123");
        ArrayList<Symbol> expected = new ArrayList<>(1);
        expected.add(new NumberAtom(123));
        assertEquals(expected, parsed);
    }

    @Test
    public void parsePlusSign(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("+");
        ArrayList<Symbol> expected = new ArrayList<>(1);
        expected.add(new AdditionOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parseMinusSign(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("-");
        ArrayList<Symbol> expected = new ArrayList<>(1);
        expected.add(new SubtractionOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parseTimesSign(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("x");
        ArrayList<Symbol> expected = new ArrayList<>(1);
        expected.add(new MultiplicationOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parseDivideSign(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("/");
        ArrayList<Symbol> expected = new ArrayList<>(1);
        expected.add(new DivisionOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parseNO(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("123+");
        ArrayList<Symbol> expected = new ArrayList<>(2);
        expected.add(new NumberAtom(123));
        expected.add(new AdditionOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parseON(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("+123");
        ArrayList<Symbol> expected = new ArrayList<>(2);
        expected.add(new AdditionOperator());
        expected.add(new NumberAtom(123));
        assertEquals(expected, parsed);
    }

    @Test
    public void parseOO(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("+-");
        ArrayList<Symbol> expected = new ArrayList<>(2);
        expected.add(new AdditionOperator());
        expected.add(new SubtractionOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parseNOON(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("123+-123");
        ArrayList<Symbol> expected = new ArrayList<>(4);
        expected.add(new NumberAtom(123));
        expected.add(new AdditionOperator());
        expected.add(new SubtractionOperator());
        expected.add(new NumberAtom(123));
        assertEquals(expected, parsed);
    }

    @Test
    public void parseOONONOO(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("x/123+-456/x");
        ArrayList<Symbol> expected = new ArrayList<>(7);
        expected.add(new MultiplicationOperator());
        expected.add(new DivisionOperator());
        expected.add(new NumberAtom(123));
        expected.add(new AdditionOperator());
        expected.add(new SubtractionOperator());
        expected.add(new NumberAtom(456));
        expected.add(new DivisionOperator());
        expected.add(new MultiplicationOperator());
        assertEquals(expected, parsed);
    }

    @Test
    public void parsePNONPOPNONP(){
        Parser parser = DaggerParser.create();
        ArrayList<Symbol> parsed = parser.input().parse("(1+2)-(3x4)");
        ArrayList<Symbol> expected = new ArrayList<>(11);
        expected.add(new LParenGrouper());
        expected.add(new NumberAtom(1));
        expected.add(new AdditionOperator());
        expected.add(new NumberAtom(2));
        expected.add(new RParenGrouper());
        expected.add(new SubtractionOperator());
        expected.add(new LParenGrouper());
        expected.add(new NumberAtom(3));
        expected.add(new MultiplicationOperator());
        expected.add(new NumberAtom(4));
        expected.add(new RParenGrouper());
        assertEquals(expected, parsed);
    }
}
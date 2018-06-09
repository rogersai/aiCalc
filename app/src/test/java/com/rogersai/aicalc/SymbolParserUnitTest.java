package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.SymbolParser;
import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;
import com.rogersai.aicalc.symbol.grouper.LParenGrouper;
import com.rogersai.aicalc.symbol.grouper.RParenGrouper;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;
import com.rogersai.aicalc.symbol.operator.DivisionOperator;
import com.rogersai.aicalc.symbol.operator.MultiplicationOperator;
import com.rogersai.aicalc.symbol.operator.SubtractionOperator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SymbolParserUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parseOneNumber() {
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse("123");
        Symbol expected = new NumberAtom(123);
        assertEquals(expected, parsed);
    }

    @Test
    public void parsePlusSign(){
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse("+");
        Symbol expected = new AdditionOperator();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseMinusSign(){
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse("-");
        Symbol expected = new SubtractionOperator();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseTimesSign(){
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse("x");
        Symbol expected = new MultiplicationOperator();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseDivideSign(){
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse("/");
        Symbol expected = new DivisionOperator();
        assertEquals(expected, parsed);
    }
    @Test
    public void parseLeftParen(){
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse("(");
        Symbol expected = new LParenGrouper();
        assertEquals(expected, parsed);
    }
    @Test
    public void parseRightParen(){
        Parser parser = DaggerParser.create();
        SymbolParser sp = parser.symbol();
        Symbol parsed = sp.parse(")");
        Symbol expected = new RParenGrouper();
        assertEquals(expected, parsed);
    }
}
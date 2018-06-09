package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.OperatorParser;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;
import com.rogersai.aicalc.symbol.operator.DivisionOperator;
import com.rogersai.aicalc.symbol.operator.MultiplicationOperator;
import com.rogersai.aicalc.symbol.operator.Operator;
import com.rogersai.aicalc.symbol.operator.SubtractionOperator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OperatorParserUnitTest {
    @Test
    public void parsePlusSign(){
        OperatorParser op = new OperatorParser();
        Operator parsed = op.parse("+");
        Operator expected = new AdditionOperator();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseMinusSign(){
        OperatorParser op = new OperatorParser();
        Operator parsed = op.parse("-");
        Operator expected = new SubtractionOperator();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseTimesSign(){
        OperatorParser op = new OperatorParser();
        Operator parsed = op.parse("x");
        Operator expected = new MultiplicationOperator();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseDivideSign(){
        OperatorParser op = new OperatorParser();
        Operator parsed = op.parse("/");
        Operator expected = new DivisionOperator();
        assertEquals(expected, parsed);
    }
}
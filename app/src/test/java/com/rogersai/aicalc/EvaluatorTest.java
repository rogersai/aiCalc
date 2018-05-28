package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;
import com.rogersai.aicalc.symbol.operator.DivisionOperator;
import com.rogersai.aicalc.symbol.operator.MultiplicationOperator;
import com.rogersai.aicalc.symbol.operator.SubtractionOperator;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EvaluatorTest {
    @Test
    public void twoPlusTwoEqualsFour(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(4);
        assertEquals(expected, evaluated);
    }

    @Test
    public void twoPlusTwoPlusTwoEqualsSix(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(6);
        assertEquals(expected, evaluated);
    }

    @Test
    public void fourTwosEqualsEight(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(8);
        assertEquals(expected, evaluated);
    }
    @Test
    public void add1p2p3p4p5Equals15(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(1));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(2));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(3));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(4));
        input.add(new AdditionOperator());
        input.add(new NumberAtom(5));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(15);
        assertEquals(expected, evaluated);
    }

    @Test
    public void twoMinusOneEqualsOne(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(2));
        input.add(new SubtractionOperator());
        input.add(new NumberAtom(1));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(1);
        assertEquals(expected, evaluated);
    }

    @Test
    public void sub3m2m1Equals0(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(3));
        input.add(new SubtractionOperator());
        input.add(new NumberAtom(2));
        input.add(new SubtractionOperator());
        input.add(new NumberAtom(1));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(0);
        assertEquals(expected, evaluated);
    }

    @Test
    public void threeTimesFourEqualsTwelve(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(3));
        input.add(new MultiplicationOperator());
        input.add(new NumberAtom(4));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(12);
        assertEquals(expected, evaluated);
    }

    @Test
    public void eightDivideFourEqualsTwo(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(8));
        input.add(new DivisionOperator());
        input.add(new NumberAtom(4));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(2);
        assertEquals(expected, evaluated);
    }

    @Test
    public void oop7m3x2Equals1(){
        Parser parser = DaggerParser.create();
        Evaluator ev = new Evaluator();
        ArrayList<Symbol> input = new ArrayList<>(3);
        input.add(new NumberAtom(7));
        input.add(new SubtractionOperator());
        input.add(new NumberAtom(3));
        input.add(new MultiplicationOperator());
        input.add(new NumberAtom(2));
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(1);
        //System.out.println(((NumberAtom)evaluated).getValue());
        //System.out.println(((NumberAtom)expected).getValue());
        assertEquals(expected, evaluated);
    }
}
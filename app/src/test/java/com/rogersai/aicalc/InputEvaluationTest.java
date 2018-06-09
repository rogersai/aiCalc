package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.evaluator.InputEvaluator;
import com.rogersai.aicalc.symbol.Symbol;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InputEvaluationTest {
    @Test
    public void inev2p2E4(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("2+2");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(4);
        assertEquals(expected, evaluated);
    }
    @Test
    public void inev2p2x3m4E4(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("2+2x3-4");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(4);
        assertEquals(expected, evaluated);
    }
    @Test
    public void inevRand1(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("3x6/9+7");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(9);
        assertEquals(expected, evaluated);
    }
    @Test
    public void inevRand2(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("4/2/1/2x3x6/9+4-6x3-8/2");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(-16);
        assertEquals(expected, evaluated);
    }
    @Test
    public void inevRand3(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("(1+3)/2");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(2);
        assertEquals(expected, evaluated);
    }
    @Test
    public void inevRand4(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("((1+3)/2)x3");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(6);
        System.out.println(((NumberAtom)evaluated).getValue());
        System.out.println(((NumberAtom)expected).getValue());
        assertEquals(expected, evaluated);
    }
    @Test
    public void inevRand5(){
        Parser parser = DaggerParser.create();
        InputEvaluator ev = new InputEvaluator();
        ArrayList<Symbol> input = parser.input().parse("2+((1+3)/2)x3");
        Symbol evaluated = ev.evaluate(input);
        Symbol expected = new NumberAtom(8);
        System.out.println(((NumberAtom)evaluated).getValue());
        System.out.println(((NumberAtom)expected).getValue());
        assertEquals(expected, evaluated);
    }
}
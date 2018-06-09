package com.rogersai.aicalc.backend.parser;

import com.rogersai.aicalc.symbol.Symbol;

import java.util.ArrayList;

public class SymbolParser {
    private OperatorParser operatorParser;
    private GrouperParser grouperParser;
    private AtomParser atomParser;
    private final ArrayList<String> operators;
    private final ArrayList<String> groupers;

    public SymbolParser(OperatorParser op, GrouperParser gp, AtomParser ap) {
        operatorParser = op;
        grouperParser = gp;
        atomParser = ap;
        operators = new ArrayList<>(6);
        operators.add("+");
        operators.add("-");
        operators.add("x");
        operators.add("/");
        groupers = new ArrayList<>(2);
        groupers.add("(");
        groupers.add(")");
    }

    public Symbol parse(String input){
        if(operators.contains(input)) {
            return operatorParser.parse(input);
        } else if(groupers.contains(input)){
            return grouperParser.parse(input);
        } else {
            return atomParser.parse(input);
        }
    }
}

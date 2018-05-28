package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.Symbol;

import java.util.ArrayList;

public class InputParser {
    private SymbolParser symbolParser;
    private ArrayList<Symbol> inputList;
    private final String reserved = "+-x/()";

    public InputParser(SymbolParser sp) {
        symbolParser = sp;

    }

    public ArrayList<Symbol> parse(String input) {
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<Symbol> symbolList = new ArrayList<>();
        int cutStart = 0;
        int cutStop = 0;

        for (int i = 0; i < input.length(); i++) {
            if(reserved.indexOf(input.charAt(i)) >= 0) {
                cutStart = cutStop;
                cutStop = i;
                if (cutStop > cutStart) {
                    stringList.add(input.substring(cutStart, cutStop));
                    System.out.print("|" + stringList.get(stringList.size() - 1) + "|");
                }
                stringList.add(String.valueOf(input.charAt(cutStop)));
                System.out.print("|"+ stringList.get(stringList.size()-1) + "|");
                cutStop++;
            }
        }
        if (cutStop < input.length()) {
            stringList.add(input.substring(cutStop));
            System.out.print("|" + stringList.get(stringList.size() - 1) + "|");
        }
        System.out.println();

        for (int i = 0; i < stringList.size(); i++) {
            symbolList.add(symbolParser.parse(stringList.get(i)));
        }
        return symbolList;
    }
}

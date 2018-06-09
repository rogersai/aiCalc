package com.rogersai.aicalc.backend.parser;

import com.rogersai.aicalc.symbol.grouper.Grouper;
import com.rogersai.aicalc.symbol.grouper.LParenGrouper;
import com.rogersai.aicalc.symbol.grouper.RParenGrouper;

public class GrouperParser {
    public Grouper parse(String input){
        if (input.equals("(")) {
            return new LParenGrouper();
        } else if (input.equals(")")) {
            return new RParenGrouper();
        } else {
            return new Grouper();
        }
    }
}

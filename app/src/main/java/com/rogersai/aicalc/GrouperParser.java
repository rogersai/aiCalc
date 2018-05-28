package com.rogersai.aicalc;

import com.rogersai.aicalc.symbol.grouper.Grouper;
import com.rogersai.aicalc.symbol.grouper.LParenGrouper;
import com.rogersai.aicalc.symbol.grouper.RParenGrouper;
import com.rogersai.aicalc.symbol.operator.AdditionOperator;
import com.rogersai.aicalc.symbol.operator.DivisionOperator;
import com.rogersai.aicalc.symbol.operator.MultiplicationOperator;
import com.rogersai.aicalc.symbol.operator.Operator;
import com.rogersai.aicalc.symbol.operator.SubtractionOperator;

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

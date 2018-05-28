package com.rogersai.aicalc.symbol.grouper;

public class LParenGrouper extends Grouper {
    public LParenGrouper() {
        super();
        category = "grouper";
        type = "lparen";
    }

    @Override
    public String toString() {
        return "(";
    }
}

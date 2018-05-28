package com.rogersai.aicalc.symbol.grouper;

public class RParenGrouper extends Grouper {
    public RParenGrouper() {
        super();
        category = "grouper";
        type = "rparen";
    }

    @Override
    public String toString() {
        return ")";
    }
}

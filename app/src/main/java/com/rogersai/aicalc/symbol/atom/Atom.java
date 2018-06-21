package com.rogersai.aicalc.symbol.atom;

import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.symbol.Symbol;

import java.util.ArrayList;

public class Atom extends Symbol implements CloudGenerator{

    public Atom() {
        super();
        category = "atom";
    }
    public NumberAtom toNumber() {
        return null;
    }

    @Override
    public ArrayList<String> generateCloudItems() {
        //TODO: Implement generateCloudItems
        return null;
    }
}


package com.rogersai.aicalc.symbol.atom;

import android.support.v4.util.Pair;

import com.rogersai.aicalc.CloudGenerator;
import com.rogersai.aicalc.symbol.Symbol;

import java.util.ArrayList;

public class Atom extends Symbol implements CloudGenerator{

    public Atom() {
        super();
        category = "atom";
    }

    @Override
    public ArrayList<Pair<String, String>> generateCloudItems() {
        //TODO: Implement generateCloudItems
        return null;
    }

    public NumberAtom toNumber() {
        return null;
    }
    public DateAtom toDate() {
        return null;
    }
}


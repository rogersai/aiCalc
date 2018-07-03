package com.rogersai.aicalc.symbol.atom;

import android.support.v4.util.Pair;

import java.util.ArrayList;

public class NumberAtom extends Atom{
    private double value;

    public NumberAtom(int value) {
        super();
        type = "number";
        this.value = (double) value;
//        System.out.println("Value: " + value);
    }

    public NumberAtom(double value) {
        super();
        type = "number";
        this.value = value;
//        System.out.println("Value: " + value);
    }

    @Override
    public ArrayList<Pair<String, String>> generateCloudItems(){
        ArrayList<Pair<String, String>> cloudItems = new ArrayList<>(1);
        cloudItems.add(new Pair(String.valueOf(value), String.valueOf(value)));
        return cloudItems;
    }

    @Override
    public NumberAtom toNumber() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberAtom that = (NumberAtom) o;
//        System.out.println("This: " + getValue());
//        System.out.println("That: " + that.getValue());
        return Double.compare(that.getValue(), getValue()) == 0;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }

    public double getValue() {
        return value;
    }
}

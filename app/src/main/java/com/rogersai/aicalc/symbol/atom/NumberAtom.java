package com.rogersai.aicalc.symbol.atom;

import java.util.ArrayList;
import java.util.Objects;

public class NumberAtom extends Atom{
    private double value;

    public NumberAtom(int value) {
        super();
        type = "number";
        this.value = (double) value;
    }

    public NumberAtom(double value) {
        super();
        type = "number";
        this.value = value;
    }

    @Override
    public ArrayList<String> generateCloudItems(){
        ArrayList<String> cloudItems = new ArrayList<>(1);
        cloudItems.add(String.valueOf(value));
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
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }

    public double getValue() {
        return value;
    }
}

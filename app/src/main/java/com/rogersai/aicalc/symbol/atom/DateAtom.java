package com.rogersai.aicalc.symbol.atom;

import java.util.ArrayList;

public class DateAtom extends Atom{
    private int day;
    private String month;
    private int year;

    public DateAtom(String date) {
        //TODO: Implement constructor
        super();
    }

    @Override
    public NumberAtom toNumber() {
        //TODO: Implement toNumber
        return null;
    }

    @Override
    public ArrayList<String> generateCloudItems(){
        //TODO: Implement generateCloudItems
        return null;
    }

    @Override
    public boolean equals(Object o) {
        //TODO: Implement equals
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DateAtom that = (DateAtom) o;
//        return Double.compare(that.value, value) == 0;
        return false;
    }

    @Override
    public String toString() {
        //TODO: Implement toString
        return "";
    }

    public double getValue() {
        //TODO: Implement getValue
        return 0;
    }
}

package com.rogersai.aicalc.symbol.atom;

import java.util.ArrayList;

public class DateAtom extends Atom{
    public static final String[] MONTHS = {"NON", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private int day;
    private int month;

    private int year;

    public DateAtom(String date) {
        //TODO: Implement constructor
        super();
        type = "date";
    }
    public DateAtom(int day, int month, int year) {
        super();
        type = "date";
        this.day = day;
        this.month = month;
        this.year = year;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateAtom that = (DateAtom) o;
        boolean yearsMatch = Integer.compare(that.getYear(), getYear()) == 0;
//        System.out.println("Years match: " + yearsMatch + ": " + that.getYear() + " " + getYear());
        boolean monthsMatch = Integer.compare(that.getMonth(), getMonth()) == 0;
//        System.out.println("Months match: " + monthsMatch);
        boolean daysMatch = Integer.compare(that.getDay(), getDay()) == 0;
//        System.out.println("Days match: " + daysMatch);
        return yearsMatch && monthsMatch && daysMatch;
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
    ///////////////////////////////
    // Getters and Setters
    ///////////////////////////////
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}

package com.rogersai.aicalc.symbol.atom;

import android.support.v4.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class DateAtom extends Atom{
    public static final String[] MONTHS = {"NON", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    public static final DateTime EPOCH = new DateTime(0, DateTimeZone.UTC).withZoneRetainFields(DateTimeZone.getDefault()).withZone(DateTimeZone.UTC);
    public static final DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMMYYYY");
    private DateTime date;

    public DateAtom(String date) {
        //TODO: Implement constructor
        super();
        type = "date";
    }

    public DateAtom(int day, int month, int year) {
        super();
        type = "date";
        this.date = new DateTime(year, month, day, 0, 0, 0).withZone(DateTimeZone.UTC);
    }
    public DateAtom(DateTime dt) {
        super();
        type = "date";
        this.date = dt.withZone(DateTimeZone.UTC);
    }

    @Override
    public ArrayList<Pair<String, String>> generateCloudItems(){
        DateAtom now = new DateAtom(DateTime.now());
        //Duration timeFromNow = new Duration(this.getDate().getMillis(), now.getMillis());
        String durationFormula = this.toString() + "-" + now.toString();
        String durationDisplay = "Time Until " + this.toString();
        //TODO: Implement generateCloudItems
        return null;
    }

    @Override
    public boolean equals(Object o) {
        //TODO: Implement equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateAtom that = (DateAtom) o;
        return this.getDate().getMillis() == ((DateAtom) o).getDate().getMillis();
    }

    @Override
    public String toString() {
        //TODO: Implement toString
        return formatter.print(date.withZone(DateTimeZone.getDefault())).toUpperCase();
    }

    public double getValue() {
        //TODO: Implement getValue
        return 0;
    }
    ///////////////////////////////
    // Getters and Setters
    ///////////////////////////////
    public int getDay() {
        return date.getDayOfMonth();
    }

    public int getMonth() {
        return date.getMonthOfYear();
    }

    public int getYear() {
        return date.getYear();
    }

    public DateTime getDate() {
        return date;
    }

    @Override
    public DateAtom toDate() {
        return this;
    }

}

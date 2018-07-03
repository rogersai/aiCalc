package com.rogersai.aicalc.symbol.atom;

import android.support.v4.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class MeasurementAtom extends Atom{
    public static final String[] MONTHS = {"NON", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    public static final DateTime EPOCH_OLD = new DateTime(0, DateTimeZone.UTC);
    public static final DateTime EPOCH = new DateTime(0, DateTimeZone.UTC).withZoneRetainFields(DateTimeZone.getDefault()).withZone(DateTimeZone.UTC);
    public static final DateTime EPOCH2_Daylight = new DateTime( 0 - DateTimeZone.getDefault().getOffset(new DateTime())).withZone(DateTimeZone.UTC);
    public static final DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMMYYYY");
    private DateTime date;

    public MeasurementAtom(String date) {
        //TODO: Implement constructor
        super();
        type = "date";

    }

    public MeasurementAtom(int day, int month, int year) {
        super();
        type = "date";
        this.date = new DateTime(year, month, day, 0, 0, 0).withZone(DateTimeZone.UTC);
    }
    public MeasurementAtom(DateTime dt) {
        super();
        type = "date";
        this.date = dt.withZone(DateTimeZone.UTC);
    }

    @Override
    public NumberAtom toNumber() {
        //TODO: Implement toNumber
        return null;
    }

    @Override
    public ArrayList<Pair<String, String>> generateCloudItems(){
        //TODO: Implement generateCloudItems
        return null;
    }

    @Override
    public boolean equals(Object o) {
        //TODO: Implement equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementAtom that = (MeasurementAtom) o;
        return this.getDate().getMillis() == ((MeasurementAtom) o).getDate().getMillis();
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
    public MeasurementAtom toDate() {
        return this;
    }

}

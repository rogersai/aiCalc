package com.rogersai.aicalc.symbol.atom;

import android.support.v4.util.Pair;

import com.rogersai.aicalc.Parser;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeasurementAtom extends Atom{
    private static final String BASE_LENGTH_UNIT = "m";
    private static final String BASE_MASS_UNIT = "g";
    private static final String BASE_VOLUME_UNIT = "L";
    private static final Map<String, Double> METRIC_PREFIXES= new HashMap<String, Double>();
    private static final Map<String, Double> LENGTH_UNITS = new HashMap<String, Double>();
    private static final Map<String, Double> MASS_UNITS = new HashMap<String, Double>();
    private static final Map<String, Double> VOLUME_UNITS = new HashMap<String, Double>();
    static {
        // Generate list of metric prefixes
        METRIC_PREFIXES.put("p", (double) (10 ^ (-12)));
        METRIC_PREFIXES.put("n", (double) (10 ^ (-9)));
        METRIC_PREFIXES.put("u", (double) (10 ^ (-6)));
        METRIC_PREFIXES.put("m", (double) (10 ^ (-3)));
        METRIC_PREFIXES.put("c", (double) (10 ^ (-2)));
        METRIC_PREFIXES.put("d", (double) (10 ^ (-1)));
        METRIC_PREFIXES.put("da", (double) (10 ^ (1)));
        METRIC_PREFIXES.put("h", (double) (10 ^ (2)));
        METRIC_PREFIXES.put("k", (double) (10 ^ (3)));
        METRIC_PREFIXES.put("M", (double) (10 ^ (6)));
        METRIC_PREFIXES.put("G", (double) (10 ^ (9)));
        METRIC_PREFIXES.put("T", (double) (10 ^ (12)));

        // Start unit lists with base metric unit
        LENGTH_UNITS.put(BASE_LENGTH_UNIT, (double) 1);
        MASS_UNITS.put(BASE_MASS_UNIT, (double) 1);
        VOLUME_UNITS.put(BASE_VOLUME_UNIT, (double) 1);

        // Add all metric units to each list
        for (String prefix : METRIC_PREFIXES.keySet()) {
            LENGTH_UNITS.put(prefix + BASE_LENGTH_UNIT, METRIC_PREFIXES.get(prefix));
            MASS_UNITS.put(prefix + BASE_MASS_UNIT, METRIC_PREFIXES.get(prefix));
            VOLUME_UNITS.put(prefix + BASE_VOLUME_UNIT, METRIC_PREFIXES.get(prefix));
        }

        // Add english to metric conversions to each list
        LENGTH_UNITS.put("mi", 1609.3440);
        LENGTH_UNITS.put("yd", 0.9144);
        LENGTH_UNITS.put("ft", 0.3048);
        LENGTH_UNITS.put("in", 0.0254);

        MASS_UNITS.put("ton", 907184.75);
        MASS_UNITS.put("lb", 453.5924);
        MASS_UNITS.put("oz", 28.3495);

        VOLUME_UNITS.put("gal", 3.7854);
        VOLUME_UNITS.put("qt", 0.9464);
        VOLUME_UNITS.put("pt", 0.4732);
        VOLUME_UNITS.put("cp", 0.2366);
        VOLUME_UNITS.put("oz", 0.0296);
        VOLUME_UNITS.put("tbsp", 0.0148);
        VOLUME_UNITS.put("tsp", 0.0049);

        for (String item : LENGTH_UNITS.keySet()) {
            System.out.println(item + " " + LENGTH_UNITS.get(item));
        }
    }

    private double value;

    public static MeasurementAtom convertUnit(double value, String oldUnit, String newUnit) {
        return null;
    }

    private String unit;

    public MeasurementAtom(double value, String unit) {
        super();
        type = "measurement";
        this.value = value;
        this.unit = unit;
    }

    @Override
    public ArrayList<Pair<String, String>> generateCloudItems(){
        //TODO: Implement generateCloudItems
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementAtom that = (MeasurementAtom) o;
        boolean valuesMatch = this.getValue() == ((MeasurementAtom) o).getValue();
        boolean unitsMatch = this.getUnit().equals(((MeasurementAtom) o).getUnit());
        return valuesMatch && unitsMatch;
    }

    ///////////////////////////////
    // Type Casters
    ///////////////////////////////
    public String toString() {
        return String.valueOf(value) + unit;
    }

    @Override
    public NumberAtom toNumber() {
        return new NumberAtom(value);
    }

    @Override
    public MeasurementAtom toMeasurement() {
        return this;
    }
    ///////////////////////////////
    // Getters and Setters
    ///////////////////////////////
    public double getValue() {
        return value;
    }
    public String getUnit() {
        return unit;
    }

}

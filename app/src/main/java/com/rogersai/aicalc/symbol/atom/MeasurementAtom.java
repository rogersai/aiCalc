package com.rogersai.aicalc.symbol.atom;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeasurementAtom extends Atom{
    private static final String BASE_LENGTH_UNIT = "m";
    private static final String BASE_MASS_UNIT = "g";
    private static final String BASE_VOLUME_UNIT = "L";
    private static final Map<String, Double> BASE_TYPE_FACTORS = new HashMap<>();
    private static final Map<String, Double> BASE_UNIT_OUTPUTS = new HashMap<>();
    private static final Map<String, Double> METRIC_PREFIXES= new HashMap<>();
    private static final Map<String, Double> LENGTH_UNITS = new HashMap<>();
    private static final Map<String, Double> MASS_UNITS = new HashMap<>();
    private static final Map<String, Double> VOLUME_UNITS = new HashMap<>();
    private static final Map<String, Map<String, Double>> UNITS = new HashMap<>();
    static {
        // Generate conversion factors between different bases
        // Preliminary Mass - Volume conversion is based on density of water
        // Volume - Length conversion based on cubing length value
        // Length - Mass conversion combines the two above

        BASE_TYPE_FACTORS.put("length", 1000.0);
        BASE_TYPE_FACTORS.put("mass", 0.001);
        BASE_TYPE_FACTORS.put("volume", 1.0);

        // Generate list of metric prefixes

        METRIC_PREFIXES.put("p", Math.pow(10, -12));
        METRIC_PREFIXES.put("n", Math.pow(10, -9));
        METRIC_PREFIXES.put("u", Math.pow(10, -6));
        METRIC_PREFIXES.put("m", Math.pow(10, -3));
        METRIC_PREFIXES.put("c", Math.pow(10, -2));
        METRIC_PREFIXES.put("d", Math.pow(10, -1));
        METRIC_PREFIXES.put("da", Math.pow(10, 1));
        METRIC_PREFIXES.put("h", Math.pow(10, 2));
        METRIC_PREFIXES.put("k", Math.pow(10, 3));
        METRIC_PREFIXES.put("M", Math.pow(10, 6));
        METRIC_PREFIXES.put("G", Math.pow(10, 9));
        METRIC_PREFIXES.put("T", Math.pow(10, 12));

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

        UNITS.put("length", LENGTH_UNITS);
        UNITS.put("mass", MASS_UNITS);
        UNITS.put("volume", VOLUME_UNITS);
        for (String item : LENGTH_UNITS.keySet()) {
            System.out.println(item + " " + LENGTH_UNITS.get(item));
        }
    }

    private double value;
    private String unit;
    private String unitType;

    public static MeasurementAtom convert(MeasurementAtom m, String newUnit) {
        double currentValue = m.getValue();
        double conversionToBase = UNITS.get(m.getUnitType()).get(m.getUnit());
        double inputTypeFactor = BASE_TYPE_FACTORS.get(m.getUnitType());
        double outputTypeFactor = 1 / BASE_TYPE_FACTORS.get(MeasurementAtom.findUnitType(newUnit));
        double conversionFromBase = 1 / UNITS.get(findUnitType(newUnit)).get(newUnit);

        double newValue =  currentValue * conversionToBase * inputTypeFactor * outputTypeFactor * conversionFromBase;
        return new MeasurementAtom(newValue, newUnit);
    }

    public static String findUnitType(String unit){
        for ( String type : UNITS.keySet() ) {
            if(UNITS.get(type).containsKey(unit)){
                return type;
            }
        }
        return "Unknown";
    }


    public MeasurementAtom(double value, String unit) {
        super();
        type = "measurement";
        this.value = value;
        this.unit = unit;
        this.unitType = MeasurementAtom.findUnitType(unit);
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

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}

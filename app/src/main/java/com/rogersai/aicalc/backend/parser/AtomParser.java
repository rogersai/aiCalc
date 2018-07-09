package com.rogersai.aicalc.backend.parser;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtomParser {
    public static final ArrayList<String> MONTHS = new ArrayList<>(Arrays.asList("NON", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"));

    public Atom parse(String input){
        Atom atom = parseDate(input);
        if (atom != null) {
            return atom;
        }
        atom = parseMeasurement(input);
        if (atom != null) {
            return atom;
        }
        atom = parseNumber(input);
        if (atom != null) {
            return atom;
        }
        atom = new Atom();
        return atom;
    }

    public NumberAtom parseNumber(String input) {
        NumberAtom atom = null;
        try {
            double value = Double.parseDouble(input);
            System.out.println(input + ": Parsed as number");
            atom = new NumberAtom(value);
        } catch(NumberFormatException e) {
            System.out.println(input + ": Does not match measurement format");
            //System.out.println("Number parse Exception: " + e.getMessage());
        }
        return atom;
    }

    public DateAtom parseDate(String input) {
        DateAtom atom = null;
        try {
            if (input.matches("\\d{2}[a-zA-Z]{3}\\d{4}")){
//                System.out.print("Successful date format match: ");
                int day = Integer.parseInt(input.substring(0,2));
//                System.out.print(day + " ");
                String monthString = input.substring(2,5).toUpperCase();
//                System.out.print(monthString + " ");
                int month = MONTHS.indexOf(monthString);
//                System.out.print(month + " ");
                int year = Integer.parseInt(input.substring(5,9));
//                System.out.println(year);

                System.out.println(input + ": Parsed as date");
                atom = new DateAtom(day, month, year);
            } else {
                System.out.println(input + ": Does not match date format");
            }
        } catch(NumberFormatException e) {
            System.out.println("Date parse Exception: " + e.getMessage());
        }
        return atom;
    }

    public MeasurementAtom parseMeasurement(String input) {
        // TODO: Implement
        MeasurementAtom atom = null;
        try {
            if (input.matches("(\\d*\\.?\\d+[a-zA-Z]+)|(\\d+\\.?\\d*[a-zA-Z]+)")) {
                Pattern pattern = Pattern.compile("[a-zA-Z]+");
                Matcher matcher = pattern.matcher(input);
                matcher.find();
                String unit = matcher.group(0);
                String valueString = pattern.matcher(input).replaceAll("");
                double value = Double.parseDouble(valueString);
                System.out.println(input + ": Parsed as measurement");
                atom = new MeasurementAtom(value, unit);
            } else {
                System.out.println(input + ": Does not match measurement format");
            }
        } catch(NumberFormatException e) {
            System.out.println("Measurement parse exception: " + e.getMessage());
        }

        return atom;
    }
}

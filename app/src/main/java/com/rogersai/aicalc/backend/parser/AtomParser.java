package com.rogersai.aicalc.backend.parser;

import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import java.util.ArrayList;
import java.util.Arrays;

public class AtomParser {
    public static final ArrayList<String> MONTHS = new ArrayList<>(Arrays.asList("NON", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"));

    public Atom parse(String input){
        Atom atom = parseDate(input);
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
            atom = new NumberAtom(value);
        } catch(NumberFormatException e) {
            System.out.println("Number parse Exception: " + e.getMessage());
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

                atom = new DateAtom(day, month, year);
            }
        } catch(NumberFormatException e) {
            System.out.println("Date parse Exception: " + e.getMessage());
        }
        return atom;
    }
}

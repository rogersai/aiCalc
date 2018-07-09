package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.AtomParser;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.MeasurementAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AtomParserUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parseOneNumber() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse("123.0");
        Atom expected = new NumberAtom(123.0);
        assertEquals(expected, parsed);
    }

    @Test
    public void parseOneDate() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse("10MAR2017");
        Atom expected = new DateAtom(10, 3, 2017);
        assertEquals(expected, parsed);
    }
    @Test
    public void parseMixedCaseDate() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse("05Mar2020");
        Atom expected = new DateAtom(5, 3, 2020);
        assertEquals(expected, parsed);
    }
    @Test
    public void parseMeasurement() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse("125.17lb");
        Atom expected = new MeasurementAtom(125.17, "lb");
        assertEquals(expected, parsed);
    }
    @Test
    public void parseMeasurementDecimal() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse(".17mg");
        Atom expected = new MeasurementAtom(.17, "mg");
        assertEquals(expected, parsed);
    }
    @Test
    public void parseMeasurementTrailingDecimal() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse("124.mm");
        Atom expected = new MeasurementAtom(124, "mm");
        assertEquals(expected, parsed);
    }
    @Test
    public void parseMeasurementNoDecimal() {
        AtomParser ap = new AtomParser();
        Atom parsed = ap.parse("124mm");
        Atom expected = new MeasurementAtom(124, "mm");
        assertEquals(expected, parsed);
    }
}
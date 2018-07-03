package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.AtomParser;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DateAtomUnitTest {

    @Test
    public void toStringTest() {
        DateAtom date = new DateAtom(3,4,2000);
        String actual = date.toString();
        String expected = "03APR2000";
        assertEquals(expected, actual);
    }

    @Test
    public void sameConstructorsEqual() {
        DateAtom date1 = new DateAtom(3, 4, 2000);
        DateAtom date2 = new DateAtom(3, 4, 2000);
        assertEquals(date1, date2);
    }

    @Test
    public void dateAndIntsConstructorsEqual() {
        DateAtom date1 = new DateAtom(3,4,2000);
        DateAtom date2 = new DateAtom(new DateTime(2000,4,3,0,0,0));
        assertEquals(date1, date2);
    }


}
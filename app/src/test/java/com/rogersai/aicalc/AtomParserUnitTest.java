package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.AtomParser;
import com.rogersai.aicalc.symbol.atom.Atom;
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
        Atom parsed = ap.parse("123");
        Atom expected = new NumberAtom(123);
        assertEquals(expected, parsed);
    }
}
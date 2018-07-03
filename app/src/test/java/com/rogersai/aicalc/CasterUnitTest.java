package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.evaluator.Caster;
import com.rogersai.aicalc.symbol.atom.Atom;
import com.rogersai.aicalc.symbol.atom.DateAtom;
import com.rogersai.aicalc.symbol.atom.NumberAtom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CasterUnitTest {

    @Test
    public void numberToDate() {
        Caster caster = new Caster();
        NumberAtom n = new NumberAtom(10);
        DateAtom d = new DateAtom(DateAtom.EPOCH.plusDays(10));
        Atom[] castResult = caster.cast(n, d);
        DateAtom result1 = (DateAtom) castResult[0];
        DateAtom result2 = (DateAtom) castResult[1];
        assertEquals(result1, result2);
    }

}
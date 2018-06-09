package com.rogersai.aicalc;

import com.rogersai.aicalc.backend.parser.GrouperParser;
import com.rogersai.aicalc.symbol.grouper.Grouper;
import com.rogersai.aicalc.symbol.grouper.LParenGrouper;
import com.rogersai.aicalc.symbol.grouper.RParenGrouper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GrouperParserUnitTest {
    @Test
    public void parseLeftParen(){
        GrouperParser gp = new GrouperParser();
        Grouper parsed = gp.parse("(");
        Grouper expected = new LParenGrouper();
        assertEquals(expected, parsed);
    }

    @Test
    public void parseRightParen(){
        GrouperParser gp = new GrouperParser();
        Grouper parsed = gp.parse(")");
        Grouper expected = new RParenGrouper();
        assertEquals(expected, parsed);
    }
}
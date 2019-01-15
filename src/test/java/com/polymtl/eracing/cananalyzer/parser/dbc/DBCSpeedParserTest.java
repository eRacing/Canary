package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.error.ParserException;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSpeedParser.DBCSpeed;

public class DBCSpeedParserTest {
    /**
     * The maximum delta when comparing floating point numbers.
     */
    public final float DELTA = 0.00001f;

    @Test
    public void testParser() {
        DBCSpeed speed;

        /* valid input */
        speed = DBCSpeedParser.PARSER.parse("BS_: 500000");
        assertEquals(500000, speed.fSpeed, DELTA);

        /* valid input with spaces */
        speed = DBCSpeedParser.PARSER.parse("BS_  :    500000");
        assertEquals(500000, speed.fSpeed, DELTA);

        /* invalid input */
        try {
            speed = DBCSpeedParser.PARSER.parse("BS_   500000");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            speed = DBCSpeedParser.PARSER.parse("BS:   500000");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            speed = DBCSpeedParser.PARSER.parse("BS_: ");
            assertTrue(false);
        } catch(ParserException e) {
        }
    }

}
package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.error.ParserException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBCVersionTest {
    @Test
    public void testParser() {
        String version;

        /* single space */
        version = DBCVersion.PARSER.parse("VERSION \"1.2.3\"");
        assertEquals("1.2.3", version);

        /* multiple spaces */
        version = DBCVersion.PARSER.parse("VERSION \"1.2.3\"");
        assertEquals("1.2.3", version);

        /* missing quote left  */
        try {
            DBCVersion.PARSER.parse("VERSION 1.2.3\"");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* missing quote right  */
        try {
            DBCVersion.PARSER.parse("VERSION \"1.2.3");
            assertTrue(false);
        } catch (ParserException e) {
        }
    }
}
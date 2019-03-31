package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.error.ParserException;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCVersionParser.DBCVersion;

public class DBCVersionParserTest {
    @Test
    public void testParser() {
        DBCVersion version;

        /* single space */
        version = DBCVersionParser.PARSER.parse("VERSION \"1.2.3\"");
        assertEquals("1.2.3", version.fVersion);

        /* multiple spaces */
        version = DBCVersionParser.PARSER.parse("VERSION \"1.2.3\"");
        assertEquals("1.2.3", version.fVersion);

        /* missing quote left  */
        try {
            DBCVersionParser.PARSER.parse("VERSION 1.2.3\"");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* missing quote right  */
        try {
            DBCVersionParser.PARSER.parse("VERSION \"1.2.3");
            assertTrue(false);
        } catch (ParserException e) {
        }
    }
}
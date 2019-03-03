package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.functional.tuple.Tuple;

import org.jparsec.error.ParserException;
import org.junit.Test;

import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.Assert.*;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCValueTableParser.DBCValueTable;

public class DBCValueTableParserTest {
    @Test
    public void testValueTableParser() {
        DBCValueTable result;

        /* expected table */
        Dictionary<Integer, String> expected = new Hashtable<>();
        expected.put(40, "hello");
        expected.put(1337, "world");
        expected.put(823, "haskell");

        /* simple case */
        result = DBCValueTableParser.PARSER.parse("VAL_TABLE_ TABLE_NAME 40 \"hello\" 1337 \"world\" 823 \"haskell\"");
        assertEquals("TABLE_NAME", result.fName);
        assertEquals(expected, result.fTable);

        /* simple case with some spaces */
        result = DBCValueTableParser.PARSER.parse("VAL_TABLE_  TABLE_NAME    40   \"hello\"   1337    \"world\"   823    \"haskell\"");
        assertEquals("TABLE_NAME", result.fName);
        assertEquals(expected, result.fTable);

        /* invalid prefix */
        try {
            DBCValueTableParser.PARSER.parse("VAL_TABLE TABLE_NAME 40 \"hello\" 1337 \"world\" 823 \"haskell\"");
            assertTrue(false);
        } catch (ParserException e) {
        }
        
        /* missing quote */
        try {
            DBCValueTableParser.PARSER.parse("VAL_TABLE_ TABLE_NAME 40 \"hello 1337 \"world\" 823 \"haskell\"");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* missing name */
        try {
            DBCValueTableParser.PARSER.parse("VAL_TABLE_ 40 \"hello 1337 \"world\" 823 \"haskell\"");
            assertTrue(false);
        } catch (ParserException e) {
        }
    }
}
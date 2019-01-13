package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.functional.tuple.Tuple;

import org.jparsec.error.ParserException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.*;

public class DBCValueTableParserTest {
    @Test
    public void testValueTableParser() {
        Dictionary<Integer, String> result;

        /* expected table */
        Dictionary<Integer, String> expected = new Hashtable<>();
        expected.put(40, "hello");
        expected.put(1337, "world");
        expected.put(823, "haskell");

        /* simple case */
        result = DBCValueTableParser.PARSER.parse("VAL_TABLE_ 40 \"hello\" 1337 \"world\" 823 \"haskell\"");
        assertEquals(expected, result);

        /* simple case with some spaces */
        result = DBCValueTableParser.PARSER.parse("VAL_TABLE_   40   \"hello\"   1337    \"world\"   823    \"haskell\"");
        assertEquals(expected, result);

        /* invalid prefix */
        try {
            DBCValueTableParser.PARSER.parse("VAL_TABLE 40 \"hello\" 1337 \"world\" 823 \"haskell\"");
            assertTrue(false);
        } catch (ParserException e) {
        }
        
        /* missing quote */
        try {
            DBCValueTableParser.PARSER.parse("VAL_TABLE_ 40 \"hello 1337 \"world\" 823 \"haskell\"");
            assertTrue(false);
        } catch (ParserException e) {
        }
    }
}
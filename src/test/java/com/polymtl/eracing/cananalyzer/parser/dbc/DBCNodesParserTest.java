package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.error.ParserException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCNodesParser.DBCNodes;

public class DBCNodesParserTest {
    @Test
    public void testParser() {
        DBCNodes nodes;

        /* create the expected list of nodes */
        List<String> expected = new ArrayList<>();
        expected.add("DEBUG");
        expected.add("MOTOR");
        expected.add("SENSOR");

        /* valid string */
        nodes = DBCNodesParser.PARSER.parse("BU_: DEBUG MOTOR SENSOR");
        assertEquals(expected, nodes.fNodes);

        /* valid string */
        nodes = DBCNodesParser.PARSER.parse("BU_ : DEBUG MOTOR SENSOR");
        assertEquals(expected, nodes.fNodes);

        /* valid string */
        nodes = DBCNodesParser.PARSER.parse("BU_  : DEBUG MOTOR SENSOR");
        assertEquals(expected, nodes.fNodes);

        /* valid string */
        nodes = DBCNodesParser.PARSER.parse("BU_ :   DEBUG MOTOR SENSOR");
        assertEquals(expected, nodes.fNodes);

        /* valid string */
        nodes = DBCNodesParser.PARSER.parse("BU_    :      DEBUG   MOTOR    SENSOR");
        assertEquals(expected, nodes.fNodes);

        /* invalid prefix */
        try {
            DBCNodesParser.PARSER.parse("B_: DEBUG MOTOR SENSOR");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* missing colon */
        try {
            DBCNodesParser.PARSER.parse("B_ DEBUG MOTOR SENSOR");
            assertTrue(false);
        } catch (ParserException e) {
        }
    }
}
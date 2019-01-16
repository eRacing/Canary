package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.error.ParserException;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCDatabaseDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCNodeDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCMessageDescription;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCDescriptionParser.DBCSignalDescription;

public class DBCDescriptionParserTest {
    @Test
    public void testDatabaseDescription() {
        DBCDescription obj;
        DBCDatabaseDescription description;

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_ \"test description\"");
        assertTrue(obj instanceof DBCDatabaseDescription);
        description = (DBCDatabaseDescription) obj;
        assertEquals(description.fDescription, "test description");

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_      \"test description\"");
        assertTrue(obj instanceof DBCDatabaseDescription);
        description = (DBCDatabaseDescription) obj;
        assertEquals(description.fDescription, "test description");

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ \"test description");
            assertTrue(false);
        } catch(ParserException e) {
        }
    }

    @Test
    public void testNodeDescription() {
        DBCDescription obj;
        DBCNodeDescription description;

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_ BU_ NODE_NAME \"test description\"");
        assertTrue(obj instanceof DBCNodeDescription);
        description = (DBCNodeDescription) obj;
        assertEquals(description.fDescription, "test description");
        assertEquals(description.fNode, "NODE_NAME");

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_    BU_  NODE_NAME   \"test description\"");
        assertTrue(obj instanceof DBCNodeDescription);
        description = (DBCNodeDescription) obj;
        assertEquals(description.fDescription, "test description");
        assertEquals(description.fNode, "NODE_NAME");

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM BU_ NODE_NAME \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ BU NODE_NAME \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ BU_ NODE_NAME  test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_  BU_ NODE_NAME  \"test description");
            assertTrue(false);
        } catch(ParserException e) {
        }
    }

    @Test
    public void testMessageDescription() {
        DBCDescription obj;
        DBCMessageDescription description;

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_ BO_ 1337 \"test description\"");
        assertTrue(obj instanceof DBCMessageDescription);
        description = (DBCMessageDescription) obj;
        assertEquals(description.fDescription, "test description");
        assertEquals(description.fMessage, new Integer(1337));

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_    BO_  1337   \"test description\"");
        assertTrue(obj instanceof DBCMessageDescription);
        description = (DBCMessageDescription) obj;
        assertEquals(description.fDescription, "test description");
        assertEquals(description.fMessage, new Integer(1337));

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM BO_ 1337 \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ BO 1337 \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ BO_ 1337  test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_  BU_ 1337  \"test description");
            assertTrue(false);
        } catch(ParserException e) {
        }
    }

    @Test
    public void testSignalDescription() {
        DBCDescription obj;
        DBCSignalDescription description;

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_ SG_ 1337 SIGNAL_NAME \"test description\"");
        assertTrue(obj instanceof DBCSignalDescription);
        description = (DBCSignalDescription) obj;
        assertEquals(description.fDescription, "test description");
        assertEquals(description.fMessage, new Integer(1337));
        assertEquals(description.fSignal, "SIGNAL_NAME");

        /* valid input */
        obj = DBCDescriptionParser.PARSER.parse("CM_    SG_  1337   SIGNAL_NAME  \"test description\"");
        assertTrue(obj instanceof DBCSignalDescription);
        description = (DBCSignalDescription) obj;
        assertEquals(description.fDescription, "test description");
        assertEquals(description.fMessage, new Integer(1337));
        assertEquals(description.fSignal, "SIGNAL_NAME");

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM SG_ 1337 SIGNAL_NAME \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ SG 1337 SIGNAL_NAME \"test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_ SG_ 1337  test description\"");
            assertTrue(false);
        } catch(ParserException e) {
        }

        /* invalid input */
        try {
            DBCDescriptionParser.PARSER.parse("CM_  BU_ 1337  \"test description");
            assertTrue(false);
        } catch(ParserException e) {
        }
    }
}

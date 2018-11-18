package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.error.ParserException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBCDescriptionParserTest {
    @Test
    public void testDatabaseDescription() {
        DBCDescription description;

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_ \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.DATABASE);
        assertEquals(description.getNodeName(), null);
        assertEquals(description.getMessageID(), null);
        assertEquals(description.getSignalName(), null);
        assertEquals(description.getDescription(), "test description");

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_      \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.DATABASE);
        assertEquals(description.getNodeName(), null);
        assertEquals(description.getMessageID(), null);
        assertEquals(description.getSignalName(), null);
        assertEquals(description.getDescription(), "test description");

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
        DBCDescription description;

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_ BU_ NODE_NAME \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.NODE);
        assertEquals(description.getNodeName(), "NODE_NAME");
        assertEquals(description.getMessageID(), null);
        assertEquals(description.getSignalName(), null);
        assertEquals(description.getDescription(), "test description");

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_    BU_  NODE_NAME   \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.NODE);
        assertEquals(description.getNodeName(), "NODE_NAME");
        assertEquals(description.getMessageID(), null);
        assertEquals(description.getSignalName(), null);
        assertEquals(description.getDescription(), "test description");

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
        DBCDescription description;

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_ BO_ 1337 \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.MESSAGE);
        assertEquals(description.getNodeName(), null);
        assertEquals(description.getMessageID(), new Integer(1337));
        assertEquals(description.getSignalName(), null);
        assertEquals(description.getDescription(), "test description");

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_    BO_  1337   \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.MESSAGE);
        assertEquals(description.getNodeName(), null);
        assertEquals(description.getMessageID(), new Integer(1337));
        assertEquals(description.getSignalName(), null);
        assertEquals(description.getDescription(), "test description");

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
        DBCDescription description;

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_ SG_ 1337 SIGNAL_NAME \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.SIGNAL);
        assertEquals(description.getNodeName(), null);
        assertEquals(description.getMessageID(), new Integer(1337));
        assertEquals(description.getSignalName(), "SIGNAL_NAME");
        assertEquals(description.getDescription(), "test description");

        /* valid input */
        description = DBCDescriptionParser.PARSER.parse("CM_    SG_  1337   SIGNAL_NAME  \"test description\"");
        assertEquals(description.getType(), DBCDescription.DescriptionType.SIGNAL);
        assertEquals(description.getNodeName(), null);
        assertEquals(description.getMessageID(), new Integer(1337));
        assertEquals(description.getSignalName(), "SIGNAL_NAME");
        assertEquals(description.getDescription(), "test description");

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
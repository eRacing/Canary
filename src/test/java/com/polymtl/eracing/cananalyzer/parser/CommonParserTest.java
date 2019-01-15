package com.polymtl.eracing.cananalyzer.parser;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;
import com.polymtl.eracing.cananalyzer.functional.tuple.TupleNumber;
import org.jparsec.Scanners;
import org.jparsec.error.ParserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CommonParserTest {
    /**
     * The maximum delta when comparing floating point numbers.
     */
    public final float DELTA = 0.00001f;

    @Rule
    public final ExpectedException fThrown = ExpectedException.none();

    @Test
    public void testSignedInteger() {
        /* single digit number */
        assertEquals(new Integer(2), CommonParser.SIGNED_INTEGER.parse("2"));

        /* multiple digits number */
        assertEquals(new Integer(234), CommonParser.SIGNED_INTEGER.parse("234"));

        /* single digit number with sign */
        assertEquals(new Integer(-2), CommonParser.SIGNED_INTEGER.parse("-2"));

        /* multiple digits number with sign */
        assertEquals(new Integer(-234), CommonParser.SIGNED_INTEGER.parse("-234"));

        /* invalid input */
        try {
            CommonParser.SIGNED_INTEGER.parse("2-");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* invalid input */
        try {
            CommonParser.SIGNED_INTEGER.parse("234-");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* invalid input */
        try {
            CommonParser.SIGNED_INTEGER.parse("-2-");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* invalid input */
        try {
            CommonParser.SIGNED_INTEGER.parse("-234-");
            assertTrue(false);
        } catch (ParserException e) {
        }
    }

    @Test
    public void testIntegerOrFloat() {
        EitherIntFloat number;

        /* single digit integer number */
        number = CommonParser.INTEGER_OR_FLOAT.parse("2");
        assertTrue(number.getLeft().isPresent());
        assertEquals(number.getLeft().get(), new Integer(2));

        /* multiple digits integer number */
        number = CommonParser.INTEGER_OR_FLOAT.parse("234");
        assertTrue(number.getLeft().isPresent());
        assertEquals(number.getLeft().get(), new Integer(234));

        /* single digit negative integer number */
        number = CommonParser.INTEGER_OR_FLOAT.parse("-2");
        assertTrue(number.getLeft().isPresent());
        assertEquals(number.getLeft().get(), new Integer(-2));

        /* multiple digits negative integer number */
        number = CommonParser.INTEGER_OR_FLOAT.parse("-234");
        assertTrue(number.getLeft().isPresent());
        assertEquals(number.getLeft().get(), new Integer(-234));

        /* float number */
        number = CommonParser.INTEGER_OR_FLOAT.parse("2.34");
        assertTrue(number.getRight().isPresent());
        assertEquals(number.getRight().get(), new Float(2.34), DELTA);

        /* negative float number */
        number = CommonParser.INTEGER_OR_FLOAT.parse("-2.34");
        assertTrue(number.getRight().isPresent());
        assertEquals(number.getRight().get(), new Float(-2.34), DELTA);
    }

    @Test
    public void testNumberTupleParenthesis() {
        TupleNumber tuple;

        /* test a tuple of integers */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1,4)");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* test a tuple with a integer and a float  */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1,-4.4)");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getRight().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getRight().get(), new Float(-4.4), DELTA);

        /* test a tuple with a float and a integer */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(-1.3,4)");
        assertTrue(tuple.getFirst().getRight().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getRight().get(), new Float(-1.3), DELTA);
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* test a tuple with two floats */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1.3,4.4)");
        assertTrue(tuple.getFirst().getRight().isPresent());
        assertTrue(tuple.getSecond().getRight().isPresent());
        assertEquals(tuple.getFirst().getRight().get(), new Float(1.3), DELTA);
        assertEquals(tuple.getSecond().getRight().get(), new Float(4.4), DELTA);

        /* input with spaces */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1,   4)");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* input with spaces */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1   ,4)");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* input with spaces */
        tuple = CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1   ,   4)");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* invalid input */
        fThrown.expect(ParserException.class);
        CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1.3,4.4");
        CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("1.3,4.4)");
        CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("(1.3 4)");
        CommonParser.NUMBER_TUPLE_PARENTHESIS.parse("1,2");
    }

    @Test
    public void testNumberTupleBrackets() {
        TupleNumber tuple;

        /* test a tuple of integers */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1,4]");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* test a tuple with a integer and a float  */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1,4.4]");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getRight().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getRight().get(), new Float(4.4), DELTA);

        /* test a tuple with a float and a integer */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1.3,4]");
        assertTrue(tuple.getFirst().getRight().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getRight().get(), new Float(1.3), DELTA);
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* test a tuple with two floats */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1.3,-4.4]");
        assertTrue(tuple.getFirst().getRight().isPresent());
        assertTrue(tuple.getSecond().getRight().isPresent());
        assertEquals(tuple.getFirst().getRight().get(), new Float(1.3), DELTA);
        assertEquals(tuple.getSecond().getRight().get(), new Float(-4.4), DELTA);

        /* input with spaces */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[-1,   4]");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(-1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* input with spaces */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1   ,4]");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* input with spaces */
        tuple = CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1   ,   4]");
        assertTrue(tuple.getFirst().getLeft().isPresent());
        assertTrue(tuple.getSecond().getLeft().isPresent());
        assertEquals(tuple.getFirst().getLeft().get(), new Integer(1));
        assertEquals(tuple.getSecond().getLeft().get(), new Integer(4));

        /* invalid input */
        fThrown.expect(ParserException.class);
        CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1.3,4.4");
        CommonParser.NUMBER_TUPLE_BRACKETS.parse("1.3,4.4]");
        CommonParser.NUMBER_TUPLE_BRACKETS.parse("[1.3 4]");
        CommonParser.NUMBER_TUPLE_BRACKETS.parse("1,2");
    }

    @Test
    public void testDoubleQuotedString() {
        String content;

        /* valid string */
        content = CommonParser.DOUBLE_QUOTED_STRING.parse("\"hello world\"");
        assertEquals("hello world", content);

        /* missing quote left */
        try {
            CommonParser.DOUBLE_QUOTED_STRING.parse("hello world\"");
            assertTrue(false);
        } catch (ParserException e) {
        }

        /* missing quote right */
        try {
            CommonParser.DOUBLE_QUOTED_STRING.parse("\"hello world");
            assertTrue(false);
        } catch (ParserException e) {

        }
    }

    @Test
    public void testFloat() {
        Float number;

        /* test a float */
        number = CommonParser.FLOAT.parse("1.23333");
        assertEquals(1.23333, number, DELTA);

        /* test an integer */
        number = CommonParser.FLOAT.parse("1");
        assertEquals(1, number, DELTA);

        /* test not a number */
        try {
            CommonParser.FLOAT.parse("asd");
            assertTrue(false);
        } catch (ParserException e) {

        }
    }
}
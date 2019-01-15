package com.polymtl.eracing.cananalyzer.parser;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;
import com.polymtl.eracing.cananalyzer.functional.tuple.TupleNumber;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.pattern.Patterns;

public class CommonParser {
    /**
     * This parser detects and consumes a space character.
     */
    public final static Parser<Void> SPACE = Scanners.isChar(' ');

    /**
     * This parser detects and consumes a newline character.
     */
    public final static Parser<Void> NEWLINE = Scanners.isChar('\n');

    /**
     * This parser detects and consumes multiple space characters.
     */
    public final static Parser<Void> SPACES = SPACE.skipMany();

    /**
     * This parser detects and consumes a colon character.
     */
    public final static Parser<Void> COLON = Scanners.isChar(':');

    /**
     * This parser detects and consumes a vertical bar character.
     */
    public final static Parser<Void> PIPE = Scanners.isChar('|');

    /**
     * This parser detects and consumes a at sign character.
     */
    public final static Parser<Void> AT = Scanners.isChar('@');

    /**
     * This parser detects and consumes an opening parenthesis character.
     */
    public final static Parser<Void> PARENTHESIS_OPEN = Scanners.isChar('(');

    /**
     * This parser detects and consumes an closing parenthesis character.
     */
    public final static Parser<Void> PARENTHESIS_CLOSE = Scanners.isChar(')');

    /**
     * This parser detects and consumes an opening bracket character.
     */
    public final static Parser<Void> BRACKET_OPEN = Scanners.isChar('[');

    /**
     * This parser detects and consumes an closing bracket character.
     */
    public final static Parser<Void> BRACKET_CLOSE = Scanners.isChar(']');

    /**
     * This parser detects and consumes a comma character.
     */
    public final static Parser<Void> COMMA = Scanners.isChar(',');

    /**
     * This parser detects and consumes a minus character.
     */
    public final static Parser<Void> MINUS = Scanners.isChar('-');

    /**
     * This parser detects and returns a integer number.
     */
    public final static Parser<Integer> UNSIGNED_INTEGER = Scanners.INTEGER.map(s -> Integer.parseInt(s));

    /**
     * This parser detects and returns a signed integer.
     */
    public final static Parser<Integer> SIGNED_INTEGER = Patterns.regex("-?\\d+")
            .toScanner("signed integer").source().map(s -> Integer.parseInt(s));

    /**
     * This parser detects and returns a floating point number.
     */
    public final static Parser<Float> FLOAT = Scanners.DECIMAL.map(s -> Float.parseFloat(s));

    /**
     * This parser detects and returns number that can possibly be an integer or a floating point. If the content isn't
     * an integer number or a floating point number, the parser will fail before creating the Either object.
     *
     * @see EitherIntFloat
     */
    public final static Parser<EitherIntFloat> INTEGER_OR_FLOAT = MINUS.optional(null)
            .followedBy(Scanners.DECIMAL).source()
            .map(s -> EitherIntFloat.fromString(s));

    /**
     * This parser detects and returns a tuple of two numbers surrounded by parenthesis. This can detect one of the
     * following:
     * <ul>
     * <li>(Integer,Integer)</li>
     * <li>(Integer,Float)</li>
     * <li>(Float,Integer)</li>
     * <li>(Float,Float)</li>
     * </ul>
     *
     * @see TupleNumber
     */
    public final static Parser<TupleNumber> NUMBER_TUPLE_PARENTHESIS = Parsers.sequence(
            PARENTHESIS_OPEN, INTEGER_OR_FLOAT, SPACES, COMMA, SPACES, INTEGER_OR_FLOAT, PARENTHESIS_CLOSE
            , (x1, x2, x3, x4, x5, x6, x7) -> new TupleNumber(x2, x6));

    /**
     * This parser detects and returns a tuple of two numbers surrounded by brackets. This can detect one of the
     * following:
     * <ul>
     * <li>[Integer,Integer]</li>
     * <li>[Integer,Float]</li>
     * <li>[Float,Integer]</li>
     * <li>[Float,Float]</li>
     * </ul>
     *
     * @see TupleNumber
     */
    public final static Parser<TupleNumber> NUMBER_TUPLE_BRACKETS = Parsers.sequence(
            BRACKET_OPEN, INTEGER_OR_FLOAT, SPACES, COMMA, SPACES, INTEGER_OR_FLOAT, BRACKET_CLOSE
            , (x1, x2, x3, x4, x5, x6, x7) -> new TupleNumber(x2, x6));

    /**
     * This parser detects and returns a tuple of two numbers surrounded by brackets. This can detect one of the
     * following:
     * <ul>
     * <li>[Integer|Integer]</li>
     * <li>[Integer|Float]</li>
     * <li>[Float|Integer]</li>
     * <li>[Float|Float]</li>
     * </ul>
     *
     * @see TupleNumber
     */
    public final static Parser<TupleNumber> NUMBER_TUPLE_BRACKETS_PIPE = Parsers.sequence(
            BRACKET_OPEN, INTEGER_OR_FLOAT, SPACES, PIPE, SPACES, INTEGER_OR_FLOAT, BRACKET_CLOSE
            , (x1, x2, x3, x4, x5, x6, x7) -> new TupleNumber(x2, x6));

    /**
     * This parser detects and returns the content of a double quoted string.
     */
    public final static Parser<String> DOUBLE_QUOTED_STRING = Scanners.DOUBLE_QUOTE_STRING
            .map(s -> s.substring(1, s.length() - 1));

    /**
     * This parser detects and consumes an empty line.
     */
    public final static Parser<Void> EMPTY_LINE = SPACES.followedBy(NEWLINE.skipMany());
}

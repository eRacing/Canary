package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;
import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import com.polymtl.eracing.cananalyzer.signal.ByteOrder;
import com.polymtl.eracing.cananalyzer.signal.Signal;
import com.polymtl.eracing.cananalyzer.signal.Signedness;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.Terminals;
import org.jparsec.pattern.Patterns;

import java.util.List;

/**
 * Exercise:
 * <p>
 * Write a parser that can read a signal in DBC format. For example, the following line:
 * <code>
 * SG_ IO_DEBUG_test_unsigned : 0|8@1+ (1,0) [0|10] "km/h" DBG,TCS
 * </code>
 * should return a signal named "IO_DEBUG_test_unsigned" that starts a bit 0 and of length 8. It is little-endian and
 * unsigned. It has a scale of 1 and a offset of 0. The minimum is 0 and the maximum is 10. The unit is in "km/h" and
 * it is link to the nodes "DBG" and "TCS".
 *
 * @link http://socialledge.com/sjsu/index.php/DBC_Format#Simple_DBC_Message
 */
public class DBCSignalParser {
    /**
     * Method that parses a String that should hold a DBC signal.
     * @param source A String that should contain a DBC signal.
     * @return A Signal object parsed from the source.
     */
    public static Signal parse(String source) {
        Object[] signalParameters = PARSER_SIGNAL.parse(source);
        return new Signal(signalParameters[2].toString(), (Integer) signalParameters[6], (Integer) signalParameters[8], (ByteOrder) signalParameters[10]
                , (Signedness) signalParameters[11], (EitherIntFloat) signalParameters[14], (EitherIntFloat) signalParameters[16]
                , (EitherIntFloat) signalParameters[20], (EitherIntFloat) signalParameters[22], signalParameters[25].toString(), (List<String>) signalParameters[27]);
    }

    /**
     * This parser detects and consumes the string "SG_".
     */
    private final static Parser<Void> PARSER_SG_PREFIX = Parsers.or(CommonParser.SPACES.followedBy(Scanners.string("SG_")), Scanners.string("SG_"));

    /**
     * Parser that detects and returns that name of the signal.
     */
    private final static Parser<String> PARSER_SIGNAL_NAME = Scanners.IDENTIFIER;

    /**
     * Parser that detects a Big Endian character and returns a ByteOrder item.
     */
    private final static Parser<ByteOrder> PARSER_BIG_ENDIAN = Patterns.isChar('0')
            .toScanner("big endian").source().map(s -> ByteOrder.BIG_ENDIAN);

    /**
     * Parser that detects a Little Endian character and returns a ByteOrder item.
     */
    private final static Parser<ByteOrder> PARSER_LITTLE_ENDIAN = Patterns.isChar('1')
            .toScanner("little endian").source().map(s -> ByteOrder.LITTLE_ENDIAN);

    /**
     * Parser that detects either a Little or Big Endian character and returns a ByteOrder item.
     */
    private final static Parser<ByteOrder> PARSER_BYTE_ORDER = Parsers.or(PARSER_BIG_ENDIAN, PARSER_LITTLE_ENDIAN);

    /**
     * Parser that detects an unsigned character and returns a Signedness item.
     */
    private static final Parser<Signedness> PARSER_UNSIGNED = Patterns.isChar('+')
            .toScanner("unsigned").source().map(s -> Signedness.UNSIGNED);

    /**
     * Parser that detects a signed character and returns a Signedness item.
     */
    private static final Parser<Signedness> PARSER_SIGNED = Patterns.isChar('-')
            .toScanner("signed").source().map(s -> Signedness.SIGNED);

    /**
     * Parser that detects either a signed or unsigned character and returns a Signedness item.
     */
    private static final Parser<Signedness> PARSER_SIGNEDNESS = Parsers.or(PARSER_UNSIGNED, PARSER_SIGNED);

    /**
     * Parser that detects the units of the signal and returns a string representing these units.
     */
    private static final Parser<String> PARSER_UNITS = Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER;

    /**
     * Parser that detects and consumes the "DBG" suffix.
     */
    private static final Parser<List<String>> PARSER_NODES = Scanners.IDENTIFIER.followedBy(Parsers.or(CommonParser.COMMA, Parsers.EOF)).many();

    /**
     * Parser that parses a whole Signal and returns and array of objects. Each non-null object is a signal parameter that will be used
     * in the parse() method.
     */
    private final static Parser<Object[]> PARSER_SIGNAL = Parsers.array(PARSER_SG_PREFIX, CommonParser.SPACES, PARSER_SIGNAL_NAME
            ,CommonParser.SPACES, CommonParser.COLON, CommonParser.SPACES, CommonParser.INTEGER, CommonParser.PIPE, CommonParser.INTEGER
            ,CommonParser.AT, PARSER_BYTE_ORDER, PARSER_SIGNEDNESS, CommonParser.SPACES, CommonParser.PARENTHESIS_OPEN, CommonParser.INTEGER_OR_FLOAT
            ,CommonParser.COMMA, CommonParser.INTEGER_OR_FLOAT, CommonParser.PARENTHESIS_CLOSE, CommonParser.SPACES, CommonParser.BRACKET_OPEN
            ,CommonParser.INTEGER_OR_FLOAT, CommonParser.PIPE, CommonParser.INTEGER_OR_FLOAT, CommonParser.BRACKET_CLOSE, CommonParser.SPACES
            ,PARSER_UNITS, CommonParser.SPACES, PARSER_NODES);


}
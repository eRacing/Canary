package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;
import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import com.polymtl.eracing.cananalyzer.signal.Signedness;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.Terminals;
import org.jparsec.pattern.Patterns;

import java.nio.ByteOrder;
import java.util.List;

/**
 * This class contains a parser for DBC signal format.
 */
public class DBCSignalParser {
    /**
     * This parser detects and consumes the string "SG_".
     */
    private final static Parser<Void> PARSER_SG_PREFIX = Scanners.string("SG_");

    /**
     * Parser that detects and returns that name of the signal.
     */
    private final static Parser<String> PARSER_SIGNAL_NAME = Scanners.IDENTIFIER;


    /////////////////
    // Multiplexer //
    /////////////////

    /**
     * This parser detects and consumes the string "M".
     */
    private final static Parser<Void> PARSER_CAPITAL_M = Scanners.string("M");

    /**
     * This parser detects and consumes the string "m".
     */
    private final static Parser<Void> PARSER_LOWER_M = Scanners.string("m");

    /**
     * This parser detects and returns if the signal is a multiplexer.
     */
    private static final Parser<DBCSignalMultiplexer> PARSER_MULTIPLEXER = PARSER_CAPITAL_M
            .map(x -> new DBCSignalMultiplexer(true, 0));

    /**
     * This parser detects and returns if the signal is a multiplexed value.
     */
    private static final Parser<DBCSignalMultiplexer> PARSER_MULTIPLEXER_IDENTIFIER = PARSER_LOWER_M
            .next(CommonParser.UNSIGNED_INTEGER)
            .map(x -> new DBCSignalMultiplexer(false, x));

    /**
     * This parser detects and returns whether the signal is multiplexed or not.
     */
    public static final Parser<DBCSignalMultiplexer> PARSER_MULTIPLEXING = Parsers
            .or(PARSER_MULTIPLEXER, PARSER_MULTIPLEXER_IDENTIFIER).optional(null);

    /**
     * This class defines the multiplexing information concerning a signal.
     */
    public static class DBCSignalMultiplexer {
        /**
         * If the signal is a multiplexer.
         */
        public final boolean fMultiplexer;

        /**
         * The identifier of the signal if it is a multiplexed value.
         */
        public final int fMultiplexerIdentifier;

        /**
         * Constructor.
         *
         * @param multiplexer If the signal is a multiplexer.
         * @param identifier  The identifier of the signal if it is a multiplexed value.
         */
        private DBCSignalMultiplexer(boolean multiplexer, int identifier) {
            fMultiplexer = multiplexer;
            fMultiplexerIdentifier = identifier;
        }
    }

    ////////////////
    // Endianness //
    ////////////////

    /**
     * Parser that detects a big endian character and returns the bit endian type.
     */
    private final static Parser<ByteOrder> PARSER_BIG_ENDIAN = Patterns.isChar('0')
            .toScanner("big endian").source().map(s -> ByteOrder.BIG_ENDIAN);

    /**
     * Parser that detects a little endian character and returns the little endian type.
     */
    private final static Parser<ByteOrder> PARSER_LITTLE_ENDIAN = Patterns.isChar('1')
            .toScanner("little endian").source().map(s -> ByteOrder.LITTLE_ENDIAN);

    /**
     * Parser that detects and returns the endianness of the signal.
     */
    private final static Parser<ByteOrder> PARSER_BYTE_ORDER = Parsers.or(PARSER_BIG_ENDIAN, PARSER_LITTLE_ENDIAN);

    ////////////////
    // Signedness //
    ////////////////

    /**
     * Parser that detects an unsigned character and returns the unsigned type.
     */
    private static final Parser<Signedness> PARSER_UNSIGNED = Patterns.isChar('+')
            .toScanner("unsigned").source().map(s -> Signedness.UNSIGNED);

    /**
     * Parser that detects an signed character and returns the signed type.
     */
    private static final Parser<Signedness> PARSER_SIGNED = Patterns.isChar('-')
            .toScanner("signed").source().map(s -> Signedness.SIGNED);

    /**
     * Parser that detects and returns the sign of the signal.
     */
    private static final Parser<Signedness> PARSER_SIGN = Parsers.or(PARSER_UNSIGNED, PARSER_SIGNED);

    ////////////////
    // Parameters //
    ////////////////

    /**
     * This parser detects and returns the signal parameters.
     */
    private static final Parser<DBCSignalParameters> PARSER_PARAMETERS = Parsers.sequence(
            CommonParser.UNSIGNED_INTEGER.followedBy(CommonParser.PIPE),
            CommonParser.UNSIGNED_INTEGER.followedBy(CommonParser.AT),
            PARSER_BYTE_ORDER, PARSER_SIGN,
            (x1, x2, x3, x4) -> new DBCSignalParameters(x1, x2, x3, x4)
    );

    /**
     * This class defines various parameters for the signal.
     */
    public static class DBCSignalParameters {
        /**
         * The start bit of the signal position in a message.
         */
        public final long fStartBit;

        /**
         * The length of the signal in the message.
         */
        public final long fLength;

        /**
         * The byte order of the signal in the message.
         */
        public final ByteOrder fByteOrder;

        /**
         * The sign of the signal in the message.
         */
        public final Signedness fSign;

        /**
         * Constructor.
         *
         * @param start  The start bit position.
         * @param length The length of the signal.
         * @param order  The byte order.
         * @param sign   The sign of the signal
         */
        private DBCSignalParameters(long start, long length, ByteOrder order, Signedness sign) {
            fStartBit = start;
            fLength = length;
            fByteOrder = order;
            fSign = sign;
        }
    }

    ///////////////
    // Numerical //
    ///////////////

    /**
     * This parser detects and returns numerical parameters.
     */
    private static final Parser<DBCSignalNumerical> PARSER_NUMERICAL = Parsers.sequence(
            CommonParser.NUMBER_TUPLE_PARENTHESIS, CommonParser.SPACES, CommonParser.NUMBER_TUPLE_BRACKETS_PIPE,
            (x1, x2, x3) -> new DBCSignalNumerical(x1.getFirst(), x1.getSecond(), x3.getFirst(), x3.getSecond())
    );

    /**
     * This class defines the numerical parameters of a signal.
     */
    public static class DBCSignalNumerical {
        /**
         * The factor value of the signal.
         */
        public final EitherIntFloat fFactor;

        /**
         * The offset value of the signal.
         */
        public final EitherIntFloat fOffset;

        /**
         * The minimum value of the signal.
         */
        public final EitherIntFloat fMinimum;

        /**
         * The maximum value of the signal.
         */
        public final EitherIntFloat fMaximum;

        /**
         * Constructor.
         *
         * @param factor The factor of the signal.
         * @param offset The offset of the signal.
         * @param min    The minimum value.
         * @param max    The maximum value.
         */
        public DBCSignalNumerical(EitherIntFloat factor, EitherIntFloat offset, EitherIntFloat min, EitherIntFloat max) {
            fFactor = factor;
            fOffset = offset;
            fMinimum = min;
            fMaximum = max;
        }
    }

    ////////////
    // Others //
    ////////////

    /**
     * Parser that detects the units of the signal and returns a string representing these units.
     */
    private static final Parser<String> PARSER_UNITS = Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER;

    /**
     * Parser that detects and returns the list of nodes.
     */
    private static final Parser<List<String>> PARSER_NODES = Scanners.IDENTIFIER.sepBy(CommonParser.COMMA);

    //////////////////////
    // Top Level Parser //
    //////////////////////

    /**
     * This parser detects and returns the DBC signal.
     */
    public final static Parser<DBCSignal> PARSER = Parsers.sequence(
            PARSER_SG_PREFIX.followedBy(CommonParser.SPACES).next(PARSER_SIGNAL_NAME),
            CommonParser.SPACES.next(PARSER_MULTIPLEXING),
            CommonParser.SPACES.followedBy(CommonParser.COLON).followedBy(CommonParser.SPACES).next(PARSER_PARAMETERS),
            CommonParser.SPACES.next(PARSER_NUMERICAL),
            CommonParser.SPACES.next(PARSER_UNITS),
            CommonParser.SPACES.next(PARSER_NODES),
            (x1, x2, x3, x4, x5, x6) -> new DBCSignal(x1, x2, x3, x4, x5, x6)
    );

    /**
     * This class defines the parameters of a DBC signal.
     */
    public static class DBCSignal implements IDBCType {
        /**
         * The name of the signal.
         */
        public final String fName;

        /**
         * The multiplexing information of the signal.
         */
        public final DBCSignalMultiplexer fMultiplexer;

        /**
         * The parameters of the signal.
         */
        public final DBCSignalParameters fParameters;

        /**
         * The numerical parameters of the signal.
         */
        public final DBCSignalNumerical fNumerical;

        /**
         * The unit of the signal.
         */
        public final String fUnit;

        /**
         * The nodes of the signal.
         */
        public final List<String> fNodes;

        /**
         * Constructor.
         *
         * @param name        The name of the signal.
         * @param multiplexer The multiplexing information.
         * @param parameters  The parameters of the signal.
         * @param numerical   The numerical parameters.
         * @param unit        The unit of the signal.
         * @param nodes       The nodes of the signal.
         */
        public DBCSignal(String name, DBCSignalMultiplexer multiplexer, DBCSignalParameters parameters,
                         DBCSignalNumerical numerical, String unit, List<String> nodes) {
            fName = name;
            fMultiplexer = multiplexer;
            fParameters = parameters;
            fNumerical = numerical;
            fUnit = unit;
            fNodes = nodes;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }
}

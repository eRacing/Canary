package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;

import java.util.List;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser.DBCSignal;

/**
 * This class contains a parser for DBC message format.
 */
public class DBCMessageParser {
    /**
     * This parser detects and consumes the string "BO_".
     */
    private final static Parser<Void> PARSER_BO_PREFIX = Scanners.string("BO_");

    /**
     * This parser detects and returns a list of DBC signals separated by new lines.
     */
    private final static Parser<List<DBCSignal>> SIGNALS_PARSER = CommonParser.SPACES
            .next(DBCSignalParser.PARSER).followedBy(CommonParser.NEWLINE).sepBy(CommonParser.EMPTY_LINE);

    /**
     * This parser detects and returns a DBC message.
     */
    public final static Parser<DBCMessage> PARSER = Parsers.sequence(
            PARSER_BO_PREFIX.followedBy(CommonParser.SPACES).next(CommonParser.UNSIGNED_INTEGER),
            CommonParser.SPACES.next(Scanners.IDENTIFIER).followedBy(CommonParser.SPACES),
            CommonParser.COLON.followedBy(CommonParser.SPACES).next(CommonParser.UNSIGNED_INTEGER),
            CommonParser.SPACES.next(Scanners.IDENTIFIER),
            CommonParser.NEWLINE.next(SIGNALS_PARSER),
            (x1, x2, x3, x4, x5) -> new DBCMessage(x1, x2, x3, x4, x5)
    );

    /**
     * This class defines a DBC message.
     */
    public static class DBCMessage implements IDBCType {
        /**
         * This ID of the message.
         */
        public final int fID;

        /**
         * The name of the message.
         */
        public final String fName;

        /**
         * The length in bytes of the message.
         */
        public final int fLength;

        /**
         * This sending node of the message.
         */
        public final String fNode;

        /**
         * The signals contained in this message.
         */
        public final List<DBCSignal> fSignals;

        /**
         * Constructor.
         *
         * @param id      The ID of the message.
         * @param name    The name of the message.
         * @param length  The length in bytes.
         * @param node    The sending node.
         * @param signals The list of DBC signals.
         */
        private DBCMessage(int id, String name, int length, String node, List<DBCSignal> signals) {
            fID = id;
            fName = name;
            fLength = length;
            fNode = node;
            fSignals = signals;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }
}
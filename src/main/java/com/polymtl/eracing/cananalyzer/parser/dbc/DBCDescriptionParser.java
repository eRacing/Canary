package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;

/**
 * This class contains a parser for parsing description in the DBC format.
 * <p><p>
 * The following line contains a description for a database:
 * <pre><code>CM_ "DESCRIPTION"</code></pre>
 * <p><p>
 * The following line contains a description for a node:
 * <pre><code>CM_ BU_ NODE_NAME "DESCRIPTION"</code></pre>
 * <p><p>
 * The following line contains a description for a message:
 * <pre><code>CM_ BO_ MESSAGE_ID "DESCRIPTION"</code></pre>
 * <p><p>
 * The following line contains a description for a signal:
 * <pre><code>CM_ SG_ MESSAGE_ID SIGNAL_NAME "DESCRIPTION"</code></pre>
 */
final public class DBCDescriptionParser {
    /**
     * This parser detects and returns the content of a double quoted string.
     */
    public final static Parser<String> DOUBLE_QUOTED_STRING = Scanners.DOUBLE_QUOTE_STRING
            .map(s -> s.substring(1, s.length() - 1));

    /**
     * This parser detects and consumes the prefix for the description.
     */

    private final static Parser<Void> PARSER_PREFIX = Scanners.string("CM_");

    /**
     * This parser detects and consumes the prefix for the description of a node.
     */
    private final static Parser<Void> TYPE_NODE = Scanners.string("BU_");

    /**
     * This parser detects and consumes the prefix for the description of a message.
     */
    private final static Parser<Void> TYPE_MESSAGE = Scanners.string("BO_");

    /**
     * This parser detects and consumes the prefix for the description of a signal.
     */
    private final static Parser<Void> TYPE_SIGNAL = Scanners.string("SG_");

    /**
     * This parser detects and returns a description for the database.
     */
    private final static Parser<DBCDescription> PARSER_DATABASE = CommonParser.SPACES.next(DOUBLE_QUOTED_STRING)
            .map(s -> new DBCDatabaseDescription(s));

    /**
     * This parser detects and returns a description for the database.
     */
    private final static Parser<DBCNodeDescription> PARSER_NODE = TYPE_NODE.next(Parsers.sequence(
            CommonParser.SPACES.next(Scanners.IDENTIFIER),
            CommonParser.SPACES.next(DOUBLE_QUOTED_STRING),
            (name, desc) -> new DBCNodeDescription(desc, name)));

    /**
     * This parser detects and returns a description for a message.
     */
    private final static Parser<DBCMessageDescription> PARSER_MESSAGE = TYPE_MESSAGE.next(Parsers.sequence(
            CommonParser.SPACES.next(CommonParser.UNSIGNED_INTEGER),
            CommonParser.SPACES.next(DOUBLE_QUOTED_STRING),
            (id, desc) -> new DBCMessageDescription(desc, id)));

    /**
     * This parser detects and returns a description for a signal.
     */
    private final static Parser<DBCSignalDescription> PARSER_SIGNAL = TYPE_SIGNAL.next(Parsers.sequence(
            CommonParser.SPACES.next(CommonParser.UNSIGNED_INTEGER),
            CommonParser.SPACES.next(Scanners.IDENTIFIER),
            CommonParser.SPACES.next(DOUBLE_QUOTED_STRING),
            (id, name, desc) -> new DBCSignalDescription(desc, id, name)));

    /**
     * This parser detects and returns the right description object.
     */
    public final static Parser<DBCDescription> PARSER = PARSER_PREFIX
            .next(CommonParser.SPACES)
            .next(Parsers.or(PARSER_SIGNAL, PARSER_MESSAGE, PARSER_NODE, PARSER_DATABASE));

    ///////////////////////
    // Generated Objects //
    ///////////////////////

    public static abstract class DBCDescription implements IDBCType {
        public final String fDescription;

        private DBCDescription(String description) {
            fDescription = description;
        }
    }

    public static class DBCDatabaseDescription extends DBCDescription implements IDBCType {
        private DBCDatabaseDescription(String description) {
            super(description);
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class DBCNodeDescription extends DBCDescription implements IDBCType {
        public final String fNode;

        private DBCNodeDescription(String description, String node) {
            super(description);
            fNode = node;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class DBCMessageDescription extends DBCDescription implements IDBCType {
        public final Integer fMessage;

        private DBCMessageDescription(String description, Integer message) {
            super(description);
            fMessage = message;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class DBCSignalDescription extends DBCDescription implements IDBCType {
        public final Integer fMessage;
        public final String fSignal;

        private DBCSignalDescription(String description, Integer message, String signal) {
            super(description);
            fMessage = message;
            fSignal = signal;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }
}

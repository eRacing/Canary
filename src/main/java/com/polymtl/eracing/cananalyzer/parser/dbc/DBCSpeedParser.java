package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import org.jparsec.Parser;
import org.jparsec.Scanners;

/**
 * This class contains a parser for detecting the speed of the bus.
 */
public class DBCSpeedParser {
    /**
     * This parser detects and consumes the prefix for the speed.
     */
    private final static Parser<Void> PARSER_PREFIX = Scanners.string("BS_");

    /**
     * This parser detects and returns the speed.
     */
    public final static Parser<DBCSpeed> PARSER = PARSER_PREFIX
            .next(CommonParser.SPACES)
            .next(CommonParser.COLON)
            .next(CommonParser.SPACES)
            .next(CommonParser.FLOAT)
            .map(x -> new DBCSpeed(x));

    /**
     * This class defines the speed of the bus in the DBC database.
     */
    public static class DBCSpeed implements IDBCType {
        /**
         * The speed of the bus [kb/s].
         */
        public final Float fSpeed;

        /**
         * Constructor.
         *
         * @param speed The speed of the bus.
         */
        private DBCSpeed(Float speed) {
            fSpeed = speed;
        }

        @Override
        public void accept(IDBCTypeVisitor visitor) {
            visitor.visit(this);
        }
    }
}

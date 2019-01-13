package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import org.jparsec.Parser;
import org.jparsec.Scanners;

/**
 * This class contains a parser for detecting the version of the format.
 */
final public class DBCVersionParser {
    /**
     * This parser detects and consumes the prefix for the version.
     */
    private final static Parser<Void> PARSER_PREFIX = Scanners.string("VERSION");

    /**
     * This parser detects and returns the version.
     */
    public final static Parser<String> PARSER = PARSER_PREFIX
            .next(CommonParser.SPACES).next(CommonParser.DOUBLE_QUOTED_STRING);
}

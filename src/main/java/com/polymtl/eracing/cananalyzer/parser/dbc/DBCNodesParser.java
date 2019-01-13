package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.parser.CommonParser;
import org.jparsec.Parser;
import org.jparsec.Scanners;

import java.util.List;

/**
 * This class contains a parser the detecting the nodes.
 */
final public class DBCNodesParser {
    /**
     * This parser detects and consumes the prefix for the nodes.
     */
    private final static Parser<Void> PARSER_PREFIX = Scanners.string("BU_");

    /**
     * This parser detects and returns the list of nodes.
     */
    public final static Parser<List<String>> PARSER = PARSER_PREFIX
            .next(CommonParser.SPACES)
            .next(CommonParser.COLON)
            .next(CommonParser.SPACES)
            .next(Scanners.IDENTIFIER.sepBy(CommonParser.SPACES));
}

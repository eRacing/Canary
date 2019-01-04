package com.polymtl.eracing.cananalyzer.ApplicationCore.ParsingCore.Parsers.dbc;

import com.polymtl.eracing.cananalyzer.ApplicationCore.ParsingCore.Parsers.CommonParser;
import com.polymtl.eracing.cananalyzer.signal.DBCMessage;
import com.polymtl.eracing.cananalyzer.signal.DBCSignal;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.Terminals;

import java.util.List;

public class DBCMessageParser {
    private static final Parser<Void> PARSER_BO_PREFIX = Scanners.string("BO_ ");


    private static final Parser<String> PARSER_MESSAGE_NAME = Terminals.Identifier.PARSER;

    private static final Parser<DBCMessage> PARSER_DBC_MESSAGE = Parsers.array(PARSER_BO_PREFIX, CommonParser.SPACE, CommonParser.INTEGER
            , CommonParser.SPACE, Terminals.Identifier.PARSER, CommonParser.COLON, CommonParser.SPACE, CommonParser.INTEGER, CommonParser.SPACE
            , Terminals.Identifier.PARSER, CommonParser.ENDLINE, DBCSignalParser.PARSER_SIGNAL.many())
            .map(s -> new DBCMessage((Integer) s[2], (String) s[4], (Integer) s[7], (String) s[9], (List<DBCSignal>) s[11]));
}

package com.polymtl.eracing.cananalyzer;

import com.polymtl.eracing.cananalyzer.ApplicationCore.DriversCore.PEAKUSBDriver;
import com.polymtl.eracing.cananalyzer.ApplicationCore.ParsingCore.Parsers.dbc.DBCSignalParser;
import com.polymtl.eracing.cananalyzer.signal.DBCSignal;

public class Main {
    private final static String testString1 = "BO_ 500 IO_DEBUG: 4 IO\n  SG_ IO_DEBUG_test_unsigned : 0|8@1+ (1.2,0) [0|10] \"km/h\" DBG,TCS";
    private final static String testString2 = " SG_ IO_DEBUG_test_unsigned : 0|8@1+ (1.2,0) [0|10] \"km/h\" DBG,TCS";
    public static void main(String[] args) {
       // Object[] message = DBCSignalParser.parseMessage(testString1);
       // DBCSignal signal = DBCSignalParser.PARSER_SIGNAL.parse(testString2);
        DBCSignal signal = DBCSignalParser.PARSER_SIGNAL.parse(testString2);
        System.out.println(signal);
        PEAKUSBDriver test = new PEAKUSBDriver();
        test.read();
    }
}

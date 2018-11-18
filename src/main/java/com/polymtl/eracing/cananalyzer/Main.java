package com.polymtl.eracing.cananalyzer;

import com.polymtl.eracing.cananalyzer.parser.dbc.DBCMessageParser;
import com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser;
import com.polymtl.eracing.cananalyzer.signal.DBCMessage;
import com.polymtl.eracing.cananalyzer.signal.DBCSignal;

public class Main {
    private final static String testString1 = "BO_ 500 IO_DEBUG: 4 IO    \n  SG_ IO_DEBUG_test_unsigned : 0|8@1+ (-1.2,0) [0|10] \"km/h\" DBG,TCS" +
            "\n SG_ IO_DEBUG_test_signed : 8|8@1- (1,-128) [0|0] \"\" DBG   \n     SG_ IO_DEBUG_test_unsigned : 0|8@1+ (1,0) [0|0] \"\" DBG";
    private final static String testString2 = " SG_ IO_DEBUG_test_unsigned : 0|8@1+ (-1.2,0) [0|10] \"km/h\" DBG,TCS\n";
    public static void main(String[] args) {
       // Object[] message = DBCSignalParser.parseMessage(testString1);
        //DBCSignal signal = DBCSignalParser.PARSER_SIGNAL.parse(testString2);
        DBCMessage signal = DBCMessageParser.PARSER_DBC_MESSAGE.parse(testString1);
        System.out.println(signal);
    }
}

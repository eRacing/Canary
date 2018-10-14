package com.polymtl.eracing.cananalyzer.parser.dbc;

import org.jparsec.Parser;
import org.jparsec.Scanners;

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
     * This parser detects and consumes the string "SG_".
     */
    private final static Parser<Void> PARSER_SG_PREFIX = Scanners.string("SG_");

}

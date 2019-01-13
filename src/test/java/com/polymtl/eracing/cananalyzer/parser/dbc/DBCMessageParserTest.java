package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.signal.Signedness;
import org.junit.Test;

import java.nio.ByteOrder;

import static org.junit.Assert.*;

import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCMessageParser.DBCMessage;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser.DBCSignal;

public class DBCMessageParserTest {
    private void assertSignal0(DBCSignal signal) {
        assertEquals("sig_Brake2_Raw", signal.fName);
        assertEquals(null, signal.fMultiplexer);
        assertEquals(16, signal.fParameters.fStartBit);
        assertEquals(16, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("", signal.fUnit);
        assertEquals(1, signal.fNodes.size());
        assertEquals("Vector__XXX", signal.fNodes.get(0));
    }

    private void assertSignal1(DBCSignal signal) {
        assertEquals("sig_Brake1_Raw", signal.fName);
        assertEquals(null, signal.fMultiplexer);
        assertEquals(0, signal.fParameters.fStartBit);
        assertEquals(16, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("", signal.fUnit);
        assertEquals(1, signal.fNodes.size());
        assertEquals("Vector__XXX", signal.fNodes.get(0));
    }

    private void assertSignal2(DBCSignal signal) {
        assertEquals("brake_sensor2", signal.fName);
        assertEquals(null, signal.fMultiplexer);
        assertEquals(48, signal.fParameters.fStartBit);
        assertEquals(16, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("", signal.fUnit);
        assertEquals(1, signal.fNodes.size());
        assertEquals("Vector__XXX", signal.fNodes.get(0));
    }

    private void assertSignal3(DBCSignal signal) {
        assertEquals("brake_sensor1", signal.fName);
        assertEquals(null, signal.fMultiplexer);
        assertEquals(32, signal.fParameters.fStartBit);
        assertEquals(16, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("", signal.fUnit);
        assertEquals(1, signal.fNodes.size());
        assertEquals("Vector__XXX", signal.fNodes.get(0));
    }

    private void assertMessage(DBCMessage message) {
        assertEquals(132, message.fID);
        assertEquals("msg_TCS_Brakes", message.fName);
        assertEquals(8, message.fLength);
        assertEquals("TCS", message.fNode);
        assertEquals(4, message.fSignals.size());
        assertSignal0(message.fSignals.get(0));
        assertSignal1(message.fSignals.get(1));
        assertSignal2(message.fSignals.get(2));
        assertSignal3(message.fSignals.get(3));
    }

    @Test
    public void testParser() {
        String content;
        DBCMessage message;

        /* simple message */
        content = ""
                + "BO_ 132 msg_TCS_Brakes : 8 TCS\n"
                + " SG_ sig_Brake2_Raw : 16|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + " SG_ sig_Brake1_Raw : 0|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + " SG_ brake_sensor2 : 48|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + " SG_ brake_sensor1 : 32|16@1+ (1,0) [0|0] \"\" Vector__XXX\n";
        assertMessage(DBCMessageParser.PARSER.parse(content));

        /* simple message with random indentation */
        content = ""
                + "BO_ 132 msg_TCS_Brakes : 8 TCS\n"
                + "    SG_ sig_Brake2_Raw : 16|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + "SG_ sig_Brake1_Raw : 0|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + "      SG_ brake_sensor2 : 48|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + "   SG_ brake_sensor1 : 32|16@1+ (1,0) [0|0] \"\" Vector__XXX\n";
        assertMessage(DBCMessageParser.PARSER.parse(content));

        /* simple message with spaces in the message line */
        content = ""
                + "BO_    132      msg_TCS_Brakes    :    8    TCS\n"
                + " SG_ sig_Brake2_Raw : 16|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + " SG_ sig_Brake1_Raw : 0|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + " SG_ brake_sensor2 : 48|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + " SG_ brake_sensor1 : 32|16@1+ (1,0) [0|0] \"\" Vector__XXX\n";
        assertMessage(DBCMessageParser.PARSER.parse(content));

        /* simple message with random new line */
        content = ""
                + "BO_ 132 msg_TCS_Brakes : 8 TCS\n"
                + " SG_ sig_Brake2_Raw : 16|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + "\n"
                + " SG_ sig_Brake1_Raw : 0|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + "\n"
                + "\n"
                + " SG_ brake_sensor2 : 48|16@1+ (1,0) [0|0] \"\" Vector__XXX\n"
                + "  \n"
                + "\n"
                + " SG_ brake_sensor1 : 32|16@1+ (1,0) [0|0] \"\" Vector__XXX\n";
        assertMessage(DBCMessageParser.PARSER.parse(content));
    }
}
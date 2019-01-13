package com.polymtl.eracing.cananalyzer.parser.dbc;

import com.polymtl.eracing.cananalyzer.signal.Signedness;
import org.junit.Test;

import java.nio.ByteOrder;

import static org.junit.Assert.*;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser.DBCSignal;
import static com.polymtl.eracing.cananalyzer.parser.dbc.DBCSignalParser.DBCSignalMultiplexer;

public class DBCSignalParserTest {
    @Test
    public void testParser() {
        DBCSignal signal;

        /* simple signal with no multiplexed data */
        signal = DBCSignalParser.PARSER.parse("SG_ name : 0|8@1+ (-1,0) [0|0] \"km/h\" DBG");
        assertEquals("name", signal.fName);
        assertEquals(null, signal.fMultiplexer);
        assertEquals(0, signal.fParameters.fStartBit);
        assertEquals(8, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(-1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("km/h", signal.fUnit);
        assertEquals(1, signal.fNodes.size());
        assertEquals("DBG", signal.fNodes.get(0));

        /* simple signal with float values and spaces, but no multiplexed data */
        signal = DBCSignalParser.PARSER.parse("SG_  signal  :  32|8@1+  (1.0,0.0) [10.0|20.0]  \"ºC\"  DBG,TCS");
        assertEquals("signal", signal.fName);
        assertEquals(null, signal.fMultiplexer);
        assertEquals(32, signal.fParameters.fStartBit);
        assertEquals(8, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getRight().isPresent());
        assertTrue(signal.fNumerical.fOffset.getRight().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getRight().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getRight().isPresent());
        assertEquals(new Float(1.0), signal.fNumerical.fFactor.getRight().get());
        assertEquals(new Float(0.0), signal.fNumerical.fOffset.getRight().get());
        assertEquals(new Float(10.0), signal.fNumerical.fMinimum.getRight().get());
        assertEquals(new Float(20.0), signal.fNumerical.fMaximum.getRight().get());
        assertEquals("ºC", signal.fUnit);
        assertEquals(2, signal.fNodes.size());
        assertEquals("DBG", signal.fNodes.get(0));
        assertEquals("TCS", signal.fNodes.get(1));

        /* simple signal as a multiplexer with no unit and no nodes */
        signal = DBCSignalParser.PARSER.parse("SG_ multi   M   : 0|4@0- (1,0) [0|0] \"\"");
        assertEquals("multi", signal.fName);
        assertNotEquals(null, signal.fMultiplexer);
        assertEquals(true, signal.fMultiplexer.fMultiplexer);
        assertEquals(0, signal.fMultiplexer.fMultiplexerIdentifier);
        assertEquals(0, signal.fParameters.fStartBit);
        assertEquals(4, signal.fParameters.fLength);
        assertEquals(ByteOrder.BIG_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.SIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("", signal.fUnit);
        assertEquals(0, signal.fNodes.size());

        /* simple signal as a multiplexed value */
        signal = DBCSignalParser.PARSER.parse("SG_ name  m3   : 0|8@1+ (-1,0) [0|0] \"km/h\" DBG");
        assertEquals("name", signal.fName);
        assertNotEquals(null, signal.fMultiplexer);
        assertEquals(false, signal.fMultiplexer.fMultiplexer);
        assertEquals(3, signal.fMultiplexer.fMultiplexerIdentifier);
        assertEquals(0, signal.fParameters.fStartBit);
        assertEquals(8, signal.fParameters.fLength);
        assertEquals(ByteOrder.LITTLE_ENDIAN, signal.fParameters.fByteOrder);
        assertEquals(Signedness.UNSIGNED, signal.fParameters.fSign);
        assertTrue(signal.fNumerical.fFactor.getLeft().isPresent());
        assertTrue(signal.fNumerical.fOffset.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMinimum.getLeft().isPresent());
        assertTrue(signal.fNumerical.fMaximum.getLeft().isPresent());
        assertEquals(new Integer(-1), signal.fNumerical.fFactor.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fOffset.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMinimum.getLeft().get());
        assertEquals(new Integer(0), signal.fNumerical.fMaximum.getLeft().get());
        assertEquals("km/h", signal.fUnit);
        assertEquals(1, signal.fNodes.size());
        assertEquals("DBG", signal.fNodes.get(0));
    }
}
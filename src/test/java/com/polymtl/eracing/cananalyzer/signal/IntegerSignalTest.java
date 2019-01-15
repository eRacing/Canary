package com.polymtl.eracing.cananalyzer.signal;

import org.junit.Test;

import java.nio.ByteOrder;

import static org.junit.Assert.*;

public class IntegerSignalTest {
    /**
     * This is a 64 bits long number.
     */
    private static long DATA = 0b11111110_00000011_10101010_11111111_00001010_11010101_11011111_10110001l;

    @Test
    public void testGetRawValue() {
        IntegerSignal signal;

        /* extract first byte */
        signal = new IntegerSignal(0, 8, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(0b10110001, signal.getRawValue(DATA));

        /* extract 12 bits */
        signal = new IntegerSignal(12, 24, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals( 0b110101011101, signal.getRawValue(DATA));

        /* extract a single set bit */
        signal = new IntegerSignal(32, 33, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(0b1, signal.getRawValue(DATA));

        /* extract a single set bit */
        signal = new IntegerSignal(32, 33, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(0b1, signal.getRawValue(DATA));

        /* extract a single cleared bit */
        signal = new IntegerSignal(54, 55, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(0b0, signal.getRawValue(DATA));

        /* extract a entire data */
        signal = new IntegerSignal(0, 64, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(DATA, signal.getRawValue(DATA));
    }

    @Test
    public void testGetValue() {
        IntegerSignal signal;

        /* extract first byte */
        signal = new IntegerSignal(0, 8, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(new Long(0b10110001), signal.getValue(DATA));

        /* extract 12 bits */
        signal = new IntegerSignal(12, 24, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals( new Long(0b110101011101), signal.getValue(DATA));

        /* extract a single set bit */
        signal = new IntegerSignal(32, 33, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(new Long(0b1), signal.getValue(DATA));

        /* extract a single set bit */
        signal = new IntegerSignal(32, 33, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(new Long(0b1), signal.getValue(DATA));

        /* extract a single cleared bit */
        signal = new IntegerSignal(54, 55, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(new Long(0b0), signal.getValue(DATA));

        /* extract a entire data */
        signal = new IntegerSignal(0, 64, ByteOrder.LITTLE_ENDIAN, 1l, 0l);
        assertEquals(new Long(DATA), signal.getValue(DATA));
    }
}
package com.polymtl.eracing.cananalyzer.signal;

import java.nio.ByteOrder;

/**
 * This abstract class defines the basic parameters of a CAN signal.
 *
 * @param <T> The type of value the signal returns.
 */
public abstract class AbstractSignal<T> implements ISignal<T> {
    /**
     * This is the minimum bit position in a message.
     */
    private static final int MINIMUM_BIT_POSITION = 0;

    /**
     * This is the maximum bit position in a message.
     */
    private static final int MAXIMUM_BIT_POSITION = 63;

    /**
     * The start bit of the signal (inclusive).
     */
    private final int fStartBit;

    /**
     * The stop bit of the signal (exclusive).
     */
    private final int fStopBit;

    /**
     * This is the byte order of the signal.
     */
    private final ByteOrder fByteOrder;

    /**
     * Constructor.
     *
     * @param start The start bit (inclusive) of the signal.
     * @param stop  The stop bit (exclusive) of the signal.
     * @param order The byte order of the signal.
     */
    public AbstractSignal(int start, int stop, ByteOrder order) {
        /* make sure the start bit position is valid */
        if(start < MINIMUM_BIT_POSITION || start > MAXIMUM_BIT_POSITION) {
            throw new IllegalArgumentException("Start bit position is out of range.");
        }

        /* make sure the stop bit position is valid */
        if(stop < MINIMUM_BIT_POSITION || stop > MAXIMUM_BIT_POSITION+1) {
            throw new IllegalArgumentException("Stop bit position is out of range.");
        }

        fStartBit = start;
        fStopBit = stop;
        fByteOrder = order;
    }

    @Override
    public int getStartBit() {
        return fStartBit;
    }

    @Override
    public int getStopBit() {
        return fStopBit;
    }

    @Override
    public ByteOrder getByteOrder() {
        return fByteOrder;
    }
}

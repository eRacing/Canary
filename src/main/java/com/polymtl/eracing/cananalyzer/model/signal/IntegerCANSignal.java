package com.polymtl.eracing.cananalyzer.model.signal;

import java.nio.ByteOrder;

public class IntegerSignal extends AbstractNumericalSignal<Long> {
    /**
     * Constructor.
     *
     * @param start  The start bit (inclusive) of the signal.
     * @param stop   The stop bit (exclusive) of the signal.
     * @param order  The byte order of the signal.
     * @param scale  The scale of the signal.
     * @param offset The offset of the signal.
     */
    public IntegerSignal(int start, int stop, ByteOrder order, Long scale, Long offset) {
        super(start, stop, order, scale, offset);
    }

    @Override
    public Long getValue(long data) {
        /* get the raw value */
        long raw = getRawValue(data);

        /* return the integer value */
        return new Long(getOffset() + raw * getScale());
    }
}

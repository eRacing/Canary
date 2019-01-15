package com.polymtl.eracing.cananalyzer.signal;

import java.nio.ByteOrder;

/**
 * This abstract class defines a signal that can output numerical values.
 * <p>
 * In order to parse a number, we need to defines a scale and an offset. Let's assume we have a 12 bits
 * numerical signal that we want to parse from a CAN message. With 12 bits, we have a 4096 possibles
 * values.
 * <p>
 * A signal might map these values to [0, 4096[, but another might map these values to [2000, 18381[ where
 * the with steps of 4 rather than 1. It gets even more complicated when we want to map to fractional values
 * like [-1024.0, 1023.5] with steps of 0.5.
 * <p>
 * In order to solve this problem, we have to use a scale and a offset. Then, the formula to compute a number N
 * parsed from the bits B directly is N = offset + scale * B.
 *
 * @param <T> The type of the numerical value.
 */
public abstract class AbstractNumericalSignal<T extends Number> extends AbstractSignal<T> {
    /**
     * The scale of the signal.
     */
    private final T fScale;

    /**
     * The offset of the signal.
     */
    private final T fOffset;

    /**
     * Constructor.
     *
     * @param start  The start bit (inclusive) of the signal.
     * @param stop   The stop bit (exclusive) of the signal.
     * @param order  The byte order of the signal.
     * @param scale  The scale of the signal.
     * @param offset The offset of the signal.
     */
    public AbstractNumericalSignal(int start, int stop, ByteOrder order, T scale, T offset) {
        super(start, stop, order);
        fScale = scale;
        fOffset = offset;
    }

    /**
     * This method returns the scale of the signal.
     *
     * @return The scale of the signal.
     */
    public T getScale() {
        return fScale;
    }

    /**
     * This method returns the offset of the signal
     *
     * @return The offset of the value.
     */
    public T getOffset() {
        return fOffset;
    }

    @Override
    public boolean isContinuous() {
        return true;
    }
}

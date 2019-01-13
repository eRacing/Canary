package com.polymtl.eracing.cananalyzer.signal;

import com.google.common.math.LongMath;

import java.nio.ByteOrder;

/**
 * This interface defines a signal for a CAN message.
 * <p>
 * Since the maximum length of the data in a CAN message (extended or not) is 64 bits,
 * we can work only on long value rather than a BitSet. Furthermore, we use char (8 bits)
 * for representing the start and stop bits position as the maximum number can be 64 in both
 * cases.
 *
 * @param <T> The type of the value returned by this signal.
 */
public interface ISignal<T> {
    /**
     * This method returns whether the signal is of a continuous type.
     *
     * @return Whether the signal is of a continuous type.
     */
    boolean isContinuous();

    /**
     * This method returns the start bit position (inclusive) of the signal in the CAN message.
     *
     * @return The start bit position (inclusive) of the signal in the CAN message.
     */
    int getStartBit();

    /**
     * This method returns the stop bit position (exclusive) of the signal in the CAN message.
     *
     * @return The stop bit position (exclusive) of the signal in the CAN message.
     */
    int getStopBit();

    /**
     * This method returns the length of the signal in the CAN message.
     *
     * @return The length of the signal in the CAN message.
     */
    default int getLength() {
        return (getStopBit() - getStartBit());
    }

    /**
     * This method returns the raw value of the signal in the CAN message. The offset
     * in the message of the value is removed.
     *
     * @param data The CAN message we want to extract the signal.
     * @return The raw value of the signal in the CAN message.
     */
    default long getRawValue(long data) {
        /* extract the raw value */
        long mask = (LongMath.pow(2, getLength()) - 1) << getStartBit();
        long value = (data & mask) >> getStartBit();

        /* swap the bytes if the endianness of the host is different */
        if (ByteOrder.nativeOrder() != getByteOrder()) {
            data = Long.reverseBytes(data);
        }
        return value;
    }

    /**
     * This method returns the extracted signal value of a message.
     *
     * @param data The message data.
     * @return The extracted signal value of the message.
     */
    T getValue(long data);

    /**
     * This method returns the byte order of the signal in the CAN message.
     *
     * @return The byte order of the signal in the CAN message.
     */
    ByteOrder getByteOrder();
}

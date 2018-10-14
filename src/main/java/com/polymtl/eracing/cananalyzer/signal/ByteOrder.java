package com.polymtl.eracing.cananalyzer.signal;

/**
 * This enumeration defines the possible byte order of a signal.
 *
 * @link https://en.wikipedia.org/wiki/Endianness
 */
public enum ByteOrder {
    /**
     * The bit-endian byte order.
     *
     * @link https://en.wikipedia.org/wiki/Endianness#Big-endian
     */
    BIG_ENDIAN,

    /**
     * The little-endian byte order.
     *
     * @link https://en.wikipedia.org/wiki/Endianness#Little-endian
     */
    LITTLE_ENDIAN;
}

package com.polymtl.eracing.cananalyzer.signal;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;

import java.util.List;

/**
 * An object representing a DBC signal.
 */
public class Signal {
    private String fName, fUnit;
    private Integer fStartBit, fSize;
    private EitherIntFloat fScale, fOffset, fMin, fMax;
    private com.polymtl.eracing.cananalyzer.signal.Signedness fSignedness;
    private com.polymtl.eracing.cananalyzer.signal.ByteOrder fByteOrder;
    private List<String> fNodes;

    /**
     * Constructor used by the parser.
     * @param name
     * @param startBit
     * @param size
     * @param byteOrder
     * @param signednes
     * @param scale
     * @param offset
     * @param min
     * @param max
     * @param unit
     */
    public Signal(String name, Integer startBit, Integer size, com.polymtl.eracing.cananalyzer.signal.ByteOrder byteOrder,
                  com.polymtl.eracing.cananalyzer.signal.Signedness signednes, EitherIntFloat scale, EitherIntFloat offset
                  , EitherIntFloat min, EitherIntFloat max, String unit, List<String> nodes) {
        fName = name;
        fStartBit = startBit;
        fSize = size;
        fByteOrder = byteOrder;
        fSignedness = signednes;
        fScale = scale;
        fOffset = offset;
        fMin = min;
        fMax = max;
        fUnit = unit;
        fNodes = nodes;
    }


}
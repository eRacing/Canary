package com.polymtl.eracing.cananalyzer.signal;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;

import java.util.List;
import java.util.Optional;

/**
 * An object representing a DBC signal.
 */
public class DBCSignal<T> {
    private String fName, fUnit;
    private Integer fStartBit, fSize;
    private T fScale, fOffset, fMin, fMax;
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
     * @param unit
     */


    public DBCSignal(String name, Integer startBit, Integer size, com.polymtl.eracing.cananalyzer.signal.ByteOrder byteOrder,
                     com.polymtl.eracing.cananalyzer.signal.Signedness signednes, T scale, T offset, T min, T max,
                     String unit, List<String> nodes) {
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


    /**
     * Creates either a DBCFloatSignal or a DBCIntegerSignal depending on weather there is a float attribute.
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
     * @param nodes
     * @return
     */
    public static DBCSignal createInstance(String name, Integer startBit, Integer size, com.polymtl.eracing.cananalyzer.signal.ByteOrder byteOrder,
                                           com.polymtl.eracing.cananalyzer.signal.Signedness signednes, EitherIntFloat scale, EitherIntFloat offset
            , EitherIntFloat min, EitherIntFloat max, String unit, List<String> nodes) {
        // If there is a float parameter, then this is a FloatSignal
        if (scale.getLeft().equals(Optional.empty()) || offset.getLeft().equals(Optional.empty()) || min.getLeft().equals(Optional.empty())
            || max.getLeft().equals(Optional.empty())) {
            // Convert scale, offset, min, and max to floats if they aren't already
            Float scaleParam = scale.getLeft().isPresent() ? scale.getLeft().get().floatValue() : scale.getRight().get();
            Float offsetParam = offset.getLeft().isPresent() ? offset.getLeft().get().floatValue() : offset.getRight().get();
            Float minParam = min.getLeft().isPresent() ? min.getLeft().get().floatValue() : min.getRight().get();
            Float maxParam = max.getLeft().isPresent() ? max.getLeft().get().floatValue() : max.getRight().get();

            // Return the Float Signal
            return new DBCSignal<Float>(name, startBit, size, byteOrder, signednes, scaleParam, offsetParam, minParam, maxParam, unit, nodes);
        }
        // Otherwise this is an IntegerSignal
        else {
            // Return an Integer Signal
            return new DBCSignal<Integer>(name, startBit, size, byteOrder, signednes, scale.getLeft().get(), offset.getLeft().get(), min.getLeft().get()
                    , max.getLeft().get(), unit, nodes);
        }
    }


}
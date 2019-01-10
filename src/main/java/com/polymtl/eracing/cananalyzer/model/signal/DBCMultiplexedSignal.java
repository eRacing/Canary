package com.polymtl.eracing.cananalyzer.model.signal;

import java.util.List;

public class DBCMultiplexedSignal<T> extends DBCSignal<T> {
    public DBCMultiplexedSignal(String name, Integer startBit, Integer size, com.polymtl.eracing.cananalyzer.model.signal.ByteOrder byteOrder,
                                com.polymtl.eracing.cananalyzer.model.signal.Signedness signednes, T scale, T offset, T min, T max,
                                String unit, List<String> nodes) {
        super(name, startBit, size, byteOrder, signednes, scale, offset, min, max, unit, nodes);

    }

}

package com.polymtl.eracing.cananalyzer.signal;

import java.util.List;

public class DBCMessage {
    private String fMessageName, fSender;
    private Integer fID, fLength;
    private List<DBCSignal> fSignals;

    public DBCMessage(Integer id, String name, Integer length, String sender, List<DBCSignal> signals) {
        fID = id;
        fMessageName = name;
        fLength = length;
        fSender = sender;
        fSignals = signals;
    }

}

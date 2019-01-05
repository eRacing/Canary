package com.polymtl.eracing.cananalyzer.ApplicationCore.DriversCore;

import com.polymtl.eracing.cananalyzer.model.CAN.CANMessage;

import java.io.IOException;

public abstract class AbsDriver {
    public abstract CANMessage read() throws IOException;

    public abstract boolean write(CANMessage message);
}

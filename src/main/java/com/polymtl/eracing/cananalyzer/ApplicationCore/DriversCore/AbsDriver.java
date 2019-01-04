package com.polymtl.eracing.cananalyzer.ApplicationCore.DriversCore;

import com.polymtl.eracing.cananalyzer.model.CAN.CANMessage;

public abstract class AbsDriver {
    public abstract CANMessage read();
}

package com.polymtl.eracing.cananalyzer.ApplicationCore.DriversCore;

import com.polymtl.eracing.cananalyzer.libraries.PCANBasic.*;
import com.polymtl.eracing.cananalyzer.model.CAN.CANMessage;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class PEAKUSBDriver extends AbsDriver {
    private PCANBasic f_can;

    public PEAKUSBDriver() {
        f_can = new PCANBasic();
        if (!f_can.initializeAPI()) {
            System.out.println("Cannot initialize can API!");
        }
        // TODO: Find what handle, baudrate, type, and port to use
        f_can.Initialize(TPCANHandle.PCAN_USBBUS1, TPCANBaudrate.PCAN_BAUD_1M, TPCANType.PCAN_TYPE_NONE, 0, (short) 0);
    }

    @Override
    public CANMessage read() {
        TPCANMsg msg = new TPCANMsg();
        f_can.Read(TPCANHandle.PCAN_USBBUS1, msg, null);
        return new CANMessage(msg.getID(), msg.getLength(), msg.getData());
    }

    /**
     * Uninitializes the can channel used.
     */
    @Override
    public void finalize() {
        f_can.Uninitialize(TPCANHandle.PCAN_USBBUS1);
    }
}

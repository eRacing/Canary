package com.polymtl.eracing.cananalyzer.model.CAN;

public class CANMessage {
    private int f_id;
    private byte f_msgLength;
    private byte[] f_data;

    /**
     * Creates a can message.
     * @param id The id of the message
     * @param msgLength The length in bytes of the message
     * @param data An array of bytes that contain the message's data
     */
    public CANMessage(int id, byte msgLength, byte[] data) {
        f_id = id;
        f_msgLength = msgLength;
        f_data = data;
    }
}

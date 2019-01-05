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

    // TODO: Find the CAN data frame used in the car to finish this method

    /**
     * Creates a CANMessage from a string that holds bytes from a CAN message
     * @param message The message from the CAN
     */
    public CANMessage(String message) {

    }

    public int getId() {
        return f_id;
    }

    public void setId(int id) {
        f_id = id;
    }

    public byte getMsgLength() {
        return f_msgLength;
    }

    public void setMsgLength(byte msgLength) {
        f_msgLength = msgLength;
    }

    public byte[] getData() {
        return f_data;
    }

    public void setData(byte[] data) {
        f_data = data;
    }

    // TODO: Find the CAN data frame used in the car and then finish toString() to match the format
    @Override
    public String toString() {
        String result = "";
        // Sets the 8 first MSBs on a byte and then sets that byte in the string
        result +=  (byte) ((f_id & 0x7FF) >> 3);
        // Sets the 3 LSBs
        result += (byte) (f_id & 0x7);
        return result;
    }
}

package com.polymtl.eracing.cananalyzer.UI.src.sample.Message;


/* Class Message is used for tests with the UI.
* everything here is purely fictive.
*
* */

public class Message {

    private double timeStamp;
    private String TxRx;
    private int channel;
    private String message;
    private int DLC;
    private String bytes;

    public Message() {
        //Default
        channel = 1;
        timeStamp = 0;
        TxRx = "Tx";
        DLC = 0;
        message = "BS_1";
        bytes = "00 00 00 00 00 00 00 00";
    }

    public Message(int chan, int DLC, String message, String byt, double time, String tx) {
        //Default
        channel = chan;
        timeStamp = time;
        TxRx = tx;
        this.DLC = DLC;
        this.message = message ;
        bytes = byt;
    }



    public int getChannel() {
        return channel;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    public String getTxRx() {
        return TxRx;
    }

    public int getiD() {
        return DLC;
    }

    public String getMessage() {
        return message;
    }

    public String getBytes() {
        return bytes;
    }
}

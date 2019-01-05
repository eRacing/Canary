package com.polymtl.eracing.cananalyzer.ApplicationCore.DriversCore;

import com.polymtl.eracing.cananalyzer.model.CAN.CANMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class WIFIDriver extends AbsDriver {
    private Socket f_socket;
    private PrintWriter f_outputStream;
    private BufferedReader f_inputStream;

    public WIFIDriver(String address, int port) throws UnknownHostException, IOException {
        // Attempt to connect to server
        f_socket = new Socket(address, port);
        f_inputStream = new BufferedReader(new InputStreamReader(f_socket.getInputStream()));
        f_outputStream = new PrintWriter(f_socket.getOutputStream(), true);
    }

    @Override
    public CANMessage read() throws IOException{
        String input = f_inputStream.readLine();
        return new CANMessage(input);
    }

    @Override
    public boolean write(CANMessage message) {
        f_outputStream.println(message.toString());
        return true;
    }

    /**
     * Closes all connections when destroyed
     */
    @Override
    public void finalize() {
        try {
            f_inputStream.close();
            f_outputStream.close();
            f_socket.close();
        } catch (IOException e) {

        }
    }
}

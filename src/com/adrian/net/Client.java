package com.adrian.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private int id;
    private int port;
    private String address;

    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public Client( String address, int port) {
        this.address = address;
        this.port = port;

        try {
            socket = new Socket(address, port);
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

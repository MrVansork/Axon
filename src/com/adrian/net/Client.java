package com.adrian.net;

import com.adrian.util.Log;

import java.io.*;
import java.net.Socket;

public class Client {

    private int id;
    private int port;
    private String address;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    /**
     * Initialize the Socket and the in, out classes
     * @param address of the serve
     * @param port of the server
     */
    public Client( String address, int port) {
        this.address = address;
        this.port = port;

        try {
            socket = new Socket(address, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receive data in bytes
     * @return bytes received
     */
    public byte[] receive(){
        byte[] data = null;
        try {
            int len = in.readInt();
            data = new byte[len];
            in.readFully(data);
            Log.d(getClass().getName(), "Received "+len+" bytes of data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Send data in bytes
     * @param bytes to send
     */
    public void send(byte[] bytes){
        send(bytes, 0, bytes.length);
    }

    /**
     * Send data in bytes
     * @param bytes to send
     * @param offset of data in bytes
     * @param length of bytes
     */
    public void send(byte[] bytes, int offset, int length){
        try {
            out.writeInt(length);
            out.write(bytes, offset, length);
            out.flush();
            Log.d(getClass().getName(), "Sent "+length+" bytes of data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a string
     * @param string to send
     */
    public void send(String string){
        send(string.getBytes(), 0, string.getBytes().length);
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }
}

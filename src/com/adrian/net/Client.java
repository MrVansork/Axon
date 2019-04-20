package com.adrian.net;

import java.io.*;
import java.net.Socket;

public class Client {

    private int id;
    private int port;
    private String address;

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    /**
     * Initialize the Socket and the in, out classes
     * @param address of the serve
     * @param port of the server
     */
    public Client(String address, int port) {
        this.address = address;
        this.port = port;

        try {
            socket = new Socket(address, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        id = Integer.parseInt(receive().split("@@ID@@")[1]);
    }

    /**
     * Receive data by string
     * @return string received
     */
    public String receive(){
        String result = null;
        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Send data by string
     * @param text string to send
     */
    public void send(String text){
        try {
            out.write(text);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quit(){
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }
}

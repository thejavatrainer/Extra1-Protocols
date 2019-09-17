package com.frequentis.mcserby.socket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TestSocketClient {

    private final String name;
    private final String hostname;
    private final int port;

    private Socket socket;

    public TestSocketClient(String name, String hostname, int port) {
        this.name = name;
        this.hostname = hostname;
        this.port = port;
    }

    public void openConnection() {
        try {
            this.socket = new Socket(hostname, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String message) {
        if (socket != null && socket.isConnected() && !socket.isClosed()) {
            try {
                DataOutputStream dout = new DataOutputStream(this.socket.getOutputStream());
                dout.writeUTF(message);
                dout.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("socket isuses");
        }
    }

}

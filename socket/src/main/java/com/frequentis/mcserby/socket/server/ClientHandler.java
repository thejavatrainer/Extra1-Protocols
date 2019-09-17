package com.frequentis.mcserby.socket.server;

import java.io.DataInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {

    private static final int SO_TIMEOUT = 10 * 60 * 10000;
    private final Socket socket;


    public ClientHandler(Socket clientSocket) throws SocketException {
        this.socket = clientSocket;
        socket.setSoTimeout(SO_TIMEOUT);
    }

    public void run() {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            for (; ; ) {
                String text = dis.readUTF();
                System.out.println("message= " + text);
                if (text.equals("Bye")) {
                    System.out.println("It's been a pleasure talking to you!");
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}

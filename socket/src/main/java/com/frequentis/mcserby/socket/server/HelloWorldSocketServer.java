package com.frequentis.mcserby.socket.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HelloWorldSocketServer {

    private final static Executor EXECUTOR = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        int portNumber = 7777;
        new HelloWorldSocketServer().startServer(portNumber);
    }

    public void startServer(int port) {
        EXECUTOR.execute(() -> this.doStartServer(port));
    }

    public void doStartServer(int port) {
        System.out.println("starting Socket Server on port " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            for (; ; ) {
                Socket clientSocket = serverSocket.accept();
                EXECUTOR.execute(new ClientHandler(clientSocket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

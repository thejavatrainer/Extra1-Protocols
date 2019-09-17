package com.frequentis.mcserby.socket.client;


import com.frequentis.mcserby.socket.server.HelloWorldSocketServer;
import org.junit.jupiter.api.Test;

class TestSocketClientTest {

    @Test
    void testClient() throws InterruptedException {
        new HelloWorldSocketServer().startServer(7777);
        TestSocketClient client1 = new TestSocketClient("Client1", "localhost", 7777);
        client1.openConnection();
        Thread.sleep(2000);
        TestSocketClient client2 = new TestSocketClient("Client2", "localhost", 7777);
        client2.openConnection();
        client1.sendMessage("Hello!");
        Thread.sleep(2000);
        client2.sendMessage("Client2 here");
        client1.sendMessage("Bye");
        Thread.sleep(2000);
        client2.sendMessage("Bye");
        Thread.sleep(1000);
    }

}
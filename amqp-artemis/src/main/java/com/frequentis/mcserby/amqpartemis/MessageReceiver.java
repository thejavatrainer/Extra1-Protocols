package com.frequentis.mcserby.amqpartemis;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @JmsListener(destination = "serbyqueue")
    public void receiveMessage(final String receivedMessage) {
        System.out.println("Received message: " + receivedMessage);
    }
}

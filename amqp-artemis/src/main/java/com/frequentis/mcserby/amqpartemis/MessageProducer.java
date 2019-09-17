package com.frequentis.mcserby.amqpartemis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@EnableScheduling
public class MessageProducer {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage(){
        String message = "hello! " + new Random().nextInt();
        System.out.println("sending: " + message);
        this.jmsTemplate.convertAndSend("serbyqueue", message);
    }

}

package com.frequentis.mcserby.amqpartemis;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

@Configuration
@EnableJms
public class JmsConfig {

    private final String brokerURL;
    private final String brokerPassword;
    private final String brokerUsername;

    public JmsConfig(@Value("${broker.url}") String brokerURL, @Value("${broker.username}") String brokerUsername,
                     @Value("${broker.password}") String brokerPassword) {
        this.brokerURL = brokerURL;
        this.brokerUsername = brokerUsername;
        this.brokerPassword = brokerPassword;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerURL);
        connectionFactory.setPassword(brokerPassword);
        connectionFactory.setUserName(brokerUsername);
        connectionFactory.setConnectResponseTimeout(10000);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate template = new JmsTemplate();
        template.setReceiveTimeout(10000);
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws JMSException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setReceiveTimeout(10000L);
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        factory.setPubSubDomain(true);
        return factory;
    }
}
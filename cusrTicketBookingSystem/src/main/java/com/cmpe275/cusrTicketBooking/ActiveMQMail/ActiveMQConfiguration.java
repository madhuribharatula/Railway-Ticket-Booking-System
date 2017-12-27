//package com.cmpe275.cusrTicketBooking.ActiveMQMail;
//
//import javax.jms.ConnectionFactory;
//import javax.jms.Queue;
//
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.listener.DefaultMessageListenerContainer;
//import org.springframework.jms.listener.MessageListenerContainer;
//
//@Configuration
//public class ActiveMQConfiguration {
//	public static final String QUEUE_NAME = "email.queue";
//
//    @Bean
//    public MessageListenerContainer listenerContainer() {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setDestinationName(QUEUE_NAME);
//        container.setMessageListener(new MyJmsListener());
//        return container;
//    }
//    
//    
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ConnectionFactory connectionFactory =
//                new ActiveMQConnectionFactory("vm://localhost");
//        return connectionFactory;
//    }
//    
//}

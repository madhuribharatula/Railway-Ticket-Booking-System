//package com.cmpe275.cusrTicketBooking.ActiveMQMail;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.jms.JMSException;
//import javax.jms.Queue;
//
//
//@Service ("amqMsgSender")
//public class MsgSender {
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    
//   
//    public void sendMessage(EmailMsg msg) throws JMSException
//    {
//        jmsTemplate.convertAndSend(ActiveMQConfiguration, msg);
//    }
//   
//    public void setJmsTemplate(JmsTemplate template)
//    {
//        this.jmsTemplate = template;
//    }
//
//}

package com.cmpe275.cusrTicketBooking.ActiveMQMail;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;


@Service("amqMsgProducer")
public class MsgProducer {
	private EmailMsg emailMsg;
	
    public EmailMsg getEmailMsg() {
		return emailMsg;
	}

	public void setEmailMsg(EmailMsg emailMsg) {
		this.emailMsg = emailMsg;
	}

	@Autowired
    JmsTemplate jmsTemplate;

    public void produce() {

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {


                ObjectMessage message = session.createObjectMessage(emailMsg);


                return message;
            }
        });
    }
}

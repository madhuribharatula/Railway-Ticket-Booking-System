package com.cmpe275.cusrTicketBooking.ActiveMQMail;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {
	
	private EmailMsg emailMsg;
	
    public EmailMsg getEmailMsg() {
		return emailMsg;
	}

	public void setEmailMsg(EmailMsg emailMsg) {
		this.emailMsg = emailMsg;
	}


	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	@Override
	public void run(String... args) throws Exception {
		send(emailMsg);
	}

	public void send(EmailMsg msg) {
		if(msg == null) {
			return;
		}
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}

}
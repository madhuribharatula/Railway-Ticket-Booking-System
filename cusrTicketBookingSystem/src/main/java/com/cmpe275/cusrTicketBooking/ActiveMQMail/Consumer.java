package com.cmpe275.cusrTicketBooking.ActiveMQMail;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.jms.Message;
import javax.mail.internet.MimeMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@Autowired
	private JavaMailSender sender;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
	    return latch;
	}

	@JmsListener(destination = "email.queue")
	public void receiveQueue(Message message) {
		if (message instanceof ActiveMQObjectMessage)
        {
            try
            {
                EmailMsg emailMsg = (EmailMsg) ((ActiveMQObjectMessage) message).getObject();
                MimeMessage Emailmessage = sender.createMimeMessage();
        		MimeMessageHelper helper = new MimeMessageHelper(Emailmessage);
        		helper.setTo(emailMsg.getMailId());
        		helper.setText(emailMsg.getBody());
        		helper.setSubject(emailMsg.getSubject());
      		
        		latch.countDown();                
        	    sender.send(Emailmessage);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

	  

	}

}
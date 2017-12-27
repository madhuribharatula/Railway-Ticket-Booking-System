package com.cmpe275.cusrTicketBooking.ActiveMQMail;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.PostConstruct;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.internet.MimeMessage;

import java.util.Date;

public class EmailListener implements MessageListener {

	@Autowired
	private JavaMailSender sender;

    private static int counter;

    @PostConstruct
    public void initCounter () {
        System.out.println("Transformer: counter initialized");
        counter=0;
    }

    public void onMessage(Message message) {

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
      		
                Thread.sleep(10000);
                sender.send(Emailmessage);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

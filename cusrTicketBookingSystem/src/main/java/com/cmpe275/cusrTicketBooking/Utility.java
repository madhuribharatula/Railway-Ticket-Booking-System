package com.cmpe275.cusrTicketBooking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component 
public class Utility {
	
	@Autowired
	private JavaMailSender sender;
	
	public static final String cancelationSubject = "Ticket Cancellation Email";
	public static final String bookingConfirmation = "Ticket Booking Confirmation Email";
	public static final String trainCancellation = "Train cancelled and Tickets rebooked for other train";
	

	public static Date StringtoDateConvertor(String input) throws ParseException {
		if(input ==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date  parsed = format.parse(input);
        Date output = new java.sql.Date(parsed.getTime());
        return output;
	}
	public static Date StringtoDateConvertor2(String input) throws ParseException {
		if(input ==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date  parsed = format.parse(input);
		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");

		String  newFormat = smp.format(parsed);
		java.util.Date  newParesed = smp.parse(newFormat);
        Date output = new java.sql.Date(newParesed.getTime());
        return output;
	}
	public void sendEmail(String recepient, String msg, String subject) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(recepient);
		helper.setText(msg);
		helper.setSubject(subject);
		sender.send(message);
	}
	
	public static String getCurrentTime() {
		 Calendar cal = Calendar.getInstance();
	     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	     return sdf.format(cal.getTime());
	}
	
	public static Date getCurrentDate() {
//		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//		String  output = format.format(Calendar.getInstance().getTime());
//        return output;
		return new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
}

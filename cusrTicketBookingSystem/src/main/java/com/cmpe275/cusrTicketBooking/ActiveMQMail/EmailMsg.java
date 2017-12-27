package com.cmpe275.cusrTicketBooking.ActiveMQMail;

import java.io.Serializable;

public class EmailMsg implements Serializable {
	
	private String subject;
	private String body;
	private String mailId;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public EmailMsg(String subject, String body, String mailId) {
		super();
		this.subject = subject;
		this.body = body;
		this.mailId = mailId;
	}
	public EmailMsg() {
		super();
	}
	
}

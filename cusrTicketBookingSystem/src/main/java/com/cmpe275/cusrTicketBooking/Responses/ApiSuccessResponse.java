package com.cmpe275.cusrTicketBooking.Responses;


public class ApiSuccessResponse {
	
	private final String title = "Group 22 Member1: Lakshmi Bharatula  Member2: Praneeth Reddy Devi Reddy";
	private Object responseObject;
	public ApiSuccessResponse(Object  responseObject) {
		this.responseObject = responseObject;
	}

	public String getTitle() {
		return title;
	}
	
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
}

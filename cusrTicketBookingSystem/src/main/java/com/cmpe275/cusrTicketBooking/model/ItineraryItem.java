package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ItineraryItem implements Serializable{
	
	public Train train;
	public String source;
	public String destination;
	public Date journeyDate;
	public Time departureTime;
	public Time arrivalTime;
	public int ticketCount;
	public List<String> PassengerNames;
	public User user;
	public boolean isReturnTrip;
	public double price;
	
	public ItineraryItem(Train train, String source, String destination, Date journeyDate, Time departureTime,
			Time arrivalTime,int ticketCount) {
		super();
		this.train = train;
		this.source = source;
		this.destination = destination;
		this.journeyDate = journeyDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.ticketCount = ticketCount;
	}
	public ItineraryItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItineraryItem(Train train, String source, String destination, Date journeyDate) {
		// TODO Auto-generated constructor stub
		super();
		this.train = train;
		this.source = source;
		this.destination = destination;
		this.journeyDate = journeyDate;
		
	}
	
	public List<String> getPassengerNames() {
		return PassengerNames;
	}
	public void setPassengerNames(List<String> passengerNames) {
		PassengerNames = passengerNames;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Time getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isReturnTrip() {
		return isReturnTrip;
	}
	public void setReturnTrip(boolean isReturnTrip) {
		this.isReturnTrip = isReturnTrip;
	}
	public String toString() {
		return train.getTrainNumber()+" "+source+" "+destination+" "+ journeyDate+" "+ departureTime+" "+arrivalTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

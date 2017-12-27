package com.cmpe275.cusrTicketBooking.model;

import java.util.List;

public class ItineraryList {
	
	public List<Itinerary> itineraryList;

	
public List<String> passengerNames;
	
	
    public List<String> getPassengerNames() {
		return passengerNames;
	}

	public void setPassengerNames(List<String> passengerNames) {
		this.passengerNames = passengerNames;
	}
	public List<Itinerary> getItineraryList() {
		return itineraryList;
	}

	public void setItineraryList(List<Itinerary> itineraryList) {
		this.itineraryList = itineraryList;
	}
	

}

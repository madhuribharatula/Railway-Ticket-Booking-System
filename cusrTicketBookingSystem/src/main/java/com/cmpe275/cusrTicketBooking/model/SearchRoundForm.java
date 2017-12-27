package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SearchRoundForm implements Serializable{

	private List<ItineraryList> searchRoundList;
	private List<String> passengerNames;
	
	
    public List<String> getPassengerNames() {
		return passengerNames;
	}

	public void setPassengerNames(List<String> passengerNames) {
		this.passengerNames = passengerNames;
	}
	
	

	public List<ItineraryList> getSearchRoundList() {
		return searchRoundList;
	}

	public void setSearchRoundList(List<ItineraryList> searchRoundList) {
		this.searchRoundList = searchRoundList;
	}

	public SearchRoundForm() {
		super();
	}
	
}

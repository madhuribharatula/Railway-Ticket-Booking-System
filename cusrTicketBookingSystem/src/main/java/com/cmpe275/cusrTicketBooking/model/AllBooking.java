package com.cmpe275.cusrTicketBooking.model;

import java.util.List;
import java.util.Map;

public class AllBooking {

	private Map<ItChecked,List<Booking>> map;

	public Map<ItChecked, List<Booking>> getMap() {
		return map;
	}

	public void setMap(Map<ItChecked, List<Booking>> map) {
		this.map = map;
	}

	public AllBooking(Map<ItChecked, List<Booking>> map) {
		super();
		this.map = map;
	}

	public AllBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

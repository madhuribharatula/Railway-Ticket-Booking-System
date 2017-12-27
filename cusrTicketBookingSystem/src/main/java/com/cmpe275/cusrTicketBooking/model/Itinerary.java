package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.util.List;

public class Itinerary implements Serializable{
	public List<ItineraryItem> itineraryItemList;
	public double totalPrice;

	 public boolean checked=false;
		

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}
	public List<ItineraryItem> getItineraryItemList() {
		return itineraryItemList;
	}

	public void setItineraryItemList(List<ItineraryItem> itineraryItemList) {
		this.itineraryItemList = itineraryItemList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}

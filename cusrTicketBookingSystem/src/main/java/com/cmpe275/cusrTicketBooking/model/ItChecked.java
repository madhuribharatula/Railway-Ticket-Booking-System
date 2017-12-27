package com.cmpe275.cusrTicketBooking.model;

public class ItChecked {
	
	private String itenary;
	private boolean checked = false;
	public String getItenary() {
		return itenary;
	}
	public void setItenary(String itenary) {
		this.itenary = itenary;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public ItChecked(boolean checked, String itenary) {
		super();
		this.itenary = itenary;
		this.checked = checked;
	}
	public ItChecked() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

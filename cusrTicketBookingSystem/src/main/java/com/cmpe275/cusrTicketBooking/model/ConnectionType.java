package com.cmpe275.cusrTicketBooking.model;

public enum ConnectionType {
	Any(3),None(1),One(2);
	private int value;
	ConnectionType( int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}

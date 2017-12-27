package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stations")
public class Stations implements Serializable{
	
	@Id
	@NotNull(message = "Station Name must not be null")
	@Column(name = "Stationname", unique = true, nullable = false)
	private String stationName;

	public Stations() {
		super();
	}

	public Stations(String stationName) {
		super();
		this.stationName = stationName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}

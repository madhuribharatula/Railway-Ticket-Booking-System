package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "train_trip")
public class TrainTrip implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trip_id", unique = true, nullable = false)
	private int tripId;

	@ManyToOne
	@JoinColumn(name="Trainid")
	private Train train;


	@NotNull(message = "trip Date must not be null")
	@Column(name = "Trip_Date")
	private Date tripDate;
	
	@NotNull(message = "Status  must not be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private TrainStatus status;

	public TrainTrip(Train train, Date tripDate, TrainStatus status) {
		super();
		this.train = train;
		this.tripDate = tripDate;
		this.status = status;
	}

	public TrainTrip() {
		super();
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public TrainStatus getStatus() {
		return status;
	}

	public void setStatus(TrainStatus status) {
		this.status = status;
	}
	
}

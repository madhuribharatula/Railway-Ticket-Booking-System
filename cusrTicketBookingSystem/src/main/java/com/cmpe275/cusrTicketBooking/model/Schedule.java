package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "timetable")
@NamedQueries({
    @NamedQuery(name="Schedule.getArrivalTime",
                query="SELECT b FROM Schedule b WHERE b.train= :train AND b.station = :station")
})
public class Schedule implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int scheduleId;
	
	@ManyToOne
	@JoinColumn(name="Trainid")
	private Train train;

	@NotNull(message = "Arrival time must not be null")
	@Column(name = "Arrivaltime")
	private Time arrivalTime;
	
	@NotNull(message = "Departure time must not be null")
	@Column(name = "Departuretime")
	private Time departureTime;
	
	@ManyToOne
	@JoinColumn(name="Stationname",referencedColumnName="Stationname")
	private Stations station;

	public Schedule(Train train, Time arrivalTime, Time departureTime, Stations station) {
		super();
		this.train = train;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.station = station;
	}

	public Schedule() {
		super();
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Stations getStation() {
		return station;
	}

	public void setStation(Stations station) {
		this.station = station;
	}
		
}

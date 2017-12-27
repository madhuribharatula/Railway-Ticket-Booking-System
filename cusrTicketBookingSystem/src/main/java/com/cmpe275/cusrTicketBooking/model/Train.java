package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "train", uniqueConstraints = {@UniqueConstraint(columnNames={"TrainNumber"})})
public class Train implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Trainid", unique = true, nullable = false)
	private int trainId;

	@NotNull(message = "train number must not be null")
	@Column(name = "Trainnumber")
	private String trainNumber;

	@NotNull(message = "last name must not be null")
	@Column(name = "CAPACITY")
	private int capacity;
	
	@NotNull(message = "start time must not be null")
	@Column(name = "STARTTIME")
	private Time startTime;
	
	@NotNull(message = "start station must not be null")
	@Column(name = "STARTSTATION")
	private String startStation;
	
	@NotNull(message = "end station must not be null")
	@Column(name = "ENDSTATION")
	private String endStation;
	
		
	@NotNull(message = "train type must not be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "Traintype")
	private TrainType trainType;
	
	@OneToMany(mappedBy="train")
    private List<Schedule> schedules;
	
	@OneToMany(mappedBy="train")
    private List<TrainTrip> trainTrips;

	public Train() {
		super();
	}
	
	public Train(String trainNumber, int capacity, Time startTime, String startStation, String endStation,
			TrainType trainType) {
		super();
		this.trainNumber = trainNumber;
		this.capacity = capacity;
		this.startTime = startTime;
		this.startStation = startStation;
		this.endStation = endStation;
		this.trainType = trainType;
	}
	
	public List<TrainTrip> getTrainTrips() {
		return trainTrips;
	}

	public void setTrainTrips(List<TrainTrip> trainTrips) {
		this.trainTrips = trainTrips;
	}

	public TrainType getTrainType() {
		return trainType;
	}

	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}
	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}

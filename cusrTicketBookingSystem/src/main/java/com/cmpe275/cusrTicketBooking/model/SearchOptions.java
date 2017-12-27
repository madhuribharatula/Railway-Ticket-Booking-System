package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Date;

public class SearchOptions implements Serializable{
	
	private int passengerCount;
	private Date outBoundJourneyDate;
	private Date returnJourneyDate;
	private String outBoundDeptTime;
	private String returnDeptTime;
	private String fromStation;
	private String toStation;
	private TrainType trainType;
	private ConnectionType connectionType;
	private int numberOfConnections;
	private boolean isRoundTrip;
	private boolean isExactTime;
	
	public boolean isExactTime() {
		return isExactTime;
	}
	public void setExactTime(boolean isExactTime) {
		this.isExactTime = isExactTime;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	
	public ConnectionType getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}
	public boolean isRoundTrip() {
		return isRoundTrip;
	}
	public void setRoundTrip(boolean isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
	}
		
	public Date getOutBoundJourneyDate() {
		return outBoundJourneyDate;
	}
	public void setOutBoundJourneyDate(Date outBoundJourneyDate) {
		this.outBoundJourneyDate = outBoundJourneyDate;
	}
	public Date getReturnJourneyDate() {
		return returnJourneyDate;
	}
	public void setReturnJourneyDate(Date returnJourneyDate) {
		this.returnJourneyDate = returnJourneyDate;
	}
	public String getOutBoundDeptTime() {
		return outBoundDeptTime;
	}
	public void setOutBoundDeptTime(String outBoundDeptTime) {
		this.outBoundDeptTime = outBoundDeptTime;
	}
	public String getReturnDeptTime() {
		return returnDeptTime;
	}
	public void setReturnDeptTime(String returnDeptTime) {
		this.returnDeptTime = returnDeptTime;
	}
	public SearchOptions() {
		super();
	}
	public SearchOptions(int passengerCount, Date outBoundJourneyDate, Date returnJourneyDate, String outBoundDeptTime,
			String returnDeptTime, String fromStation, String toStation, TrainType trainType,
			ConnectionType connectionType, boolean isRoundTrip, boolean isExactTime) {
		super();
		this.passengerCount = passengerCount;
		this.outBoundJourneyDate = outBoundJourneyDate;
		this.returnJourneyDate = returnJourneyDate;
		this.outBoundDeptTime = outBoundDeptTime;
		this.returnDeptTime = returnDeptTime;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.trainType = trainType;
		this.connectionType = connectionType;
		this.isRoundTrip = isRoundTrip;
		this.isExactTime = isExactTime;
	}
	public SearchOptions(int passengerCount_ow, Date outBoundJourneyDate_ow, String outBoundDeptTime_ow,
			String fromStation_ow, String toStation_ow, TrainType trainType_ow, ConnectionType connectionType_ow, boolean isRoundTrip_ow,
			boolean isExactTime_ow) {
		super();
		this.passengerCount = passengerCount_ow;
		this.outBoundJourneyDate = outBoundJourneyDate_ow;
		this.outBoundDeptTime = outBoundDeptTime_ow;
		this.fromStation = fromStation_ow;
		this.toStation = toStation_ow;
		this.trainType = trainType_ow;
		this.connectionType = connectionType_ow;
		this.isRoundTrip = isRoundTrip_ow;
		this.isExactTime = isExactTime_ow;
	}
	public TrainType getTrainType() {
		return trainType;
	}
	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}
	public int getNumberOfConnections() {
		return numberOfConnections;
	}
	public void setNumberOfConnections(int numberOfConnections) {
		this.numberOfConnections = numberOfConnections;
	}
	
	
	
}

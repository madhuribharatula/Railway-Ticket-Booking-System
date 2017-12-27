package com.cmpe275.cusrTicketBooking.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.cmpe275.cusrTicketBooking.CustomConverter;


@Entity
@Table(name = "booking")
@NamedQueries({
    @NamedQuery(name="Booking.findActiveBookings",
                query="SELECT b FROM Booking b WHERE b.train= :train AND b.journeyDate = :journeyDate AND b.bookingStatus = :bookingStatus")
})
public class Booking implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKING_ID", unique = true, nullable = false)
	private int bookingId;
	
	@ManyToOne
	@JoinColumn(name="SOURCE",referencedColumnName="StationName")
	private Stations sourceStation;
	
	@ManyToOne
	@JoinColumn(name="DESTINATION",referencedColumnName="StationName")
	private Stations destStation;
	
	@ManyToOne
	@JoinColumn(name="Email")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="TRAIN_ID")
	private Train train;

	@NotNull(message = "time must not be null")
	@Column(name = "Departuretime")
	private Time trainDepartureTime;
	
	@NotNull(message = "Date must not be null")
	@Column(name = "Journeydate")
	private Date journeyDate;
	
	@NotNull(message = "Ticket count must not be null")
	@Column(name = "TICKETCOUNT")
	private int ticketCount;
	
	@NotNull(message = "Price must not be null")
	@Column(name = "PRICE")
	private double price;
	
	@NotNull(message = "Price must not be null")
	@Column(name = "Totalprice")
	private double totalPrice;
	
//	@NotNull(message = "booked date must not be null")
//	@Column(name = "Bookedon")
//	@Temporal(TemporalType.TIMESTAMP)
//    private java.util.Date bookedOn;

	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	
	@Column(name = "Iitinerary_Id")
	private String itineraryId;
	
	@NotNull(message = "isReturnTrip must not be null")
	@Column(name = "Is_Return_Trip",columnDefinition = "TINYINT(1)")
	private boolean isReturnTrip;
	
	@Convert(converter = CustomConverter.class)
    private List<String> Passenger_list;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Stations getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(Stations sourceStation) {
		this.sourceStation = sourceStation;
	}

	public Stations getDestStation() {
		return destStation;
	}

	public void setDestStation(Stations destStation) {
		this.destStation = destStation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Time getTrainDepartureTime() {
		return trainDepartureTime;
	}

	public void setTrainDepartureTime(Time trainDepartureTime) {
		this.trainDepartureTime = trainDepartureTime;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

//	public java.util.Date getBookedOn() {
//		return bookedOn;
//	}
//
//	public void setBookedOn(java.util.Date bookedOn) {
//		this.bookedOn = bookedOn;
//	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(String itineraryId) {
		this.itineraryId = itineraryId;
	}

	public boolean isReturnTrip() {
		return isReturnTrip;
	}

	public void setReturnTrip(boolean isReturnTrip) {
		this.isReturnTrip = isReturnTrip;
	}

	public List<String> getPassenger_list() {
		return Passenger_list;
	}

	public void setPassenger_list(List<String> passengerNames) {
		Passenger_list = passengerNames;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(Stations sourceStation, Stations destStation, User user, Train train, Time trainDepartureTime,
			Date journeyDate, int ticketCount, double price, double totalPrice, 
			BookingStatus bookingStatus, String itineraryId, boolean isReturnTrip, List<String> passenger_list) {
		super();
		this.sourceStation = sourceStation;
		this.destStation = destStation;
		this.user = user;
		this.train = train;
		this.trainDepartureTime = trainDepartureTime;
		this.journeyDate = journeyDate;
		this.ticketCount = ticketCount;
		this.price = price;
		this.totalPrice = totalPrice;
		//this.bookedOn = bookedOn;
		this.bookingStatus = bookingStatus;
		this.itineraryId = itineraryId;
		this.isReturnTrip = isReturnTrip;
		Passenger_list = passenger_list;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("itineraryId "+ itineraryId +"\n");
		sb.append("   ");
		sb.append("bookingStatus "+bookingStatus+"\n");
		sb.append("   ");
		sb.append("sourceStation "+sourceStation.getStationName()+"\n");
		sb.append("   ");
		sb.append("destStation "+destStation.getStationName()+"\n");
		sb.append("   ");
		sb.append("price "+price+"\n");
		sb.append("   ");
		sb.append("totalPrice "+totalPrice+"\n");
		sb.append("   ");
		sb.append("trainDepartureTime "+ trainDepartureTime+"\n");
		sb.append("   ");
		sb.append("journeyDate "+journeyDate+"\n");
		sb.append("   ");
		
		sb.append("ticketCount "+ ticketCount+"\n");
		sb.append("   ");
		
		sb.append("Passenger Names : " + Passenger_list);
		
		return sb.toString();
		
	}
	

}
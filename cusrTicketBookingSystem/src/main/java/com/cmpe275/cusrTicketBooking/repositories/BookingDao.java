package com.cmpe275.cusrTicketBooking.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.BookingStatus;
import com.cmpe275.cusrTicketBooking.model.ItChecked;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.User;

public interface BookingDao {

	void bookTicket(Booking bookingRecord);

	List<Booking> readBookings(Train train, Date journeyDate, BookingStatus status);

	void bookTicket(List<Booking> bookingList);

	void updateItineray(List<Booking> bookingList, String string, double totalPrice);

	List<Booking> cancelTickets(User user, String itineraryId);

	List<Booking> getBookingFromItinerary(User user, String itineraryId);
    
	Map<ItChecked,List<Booking>> getALLBookingsForUser(User user);
	
	List<Booking> getBookingsFor(Train train, Date journeyDate);

	void resetBookings();

	List<Booking> getBookingFromTraintrip(Train train, Date journeyDate);

}

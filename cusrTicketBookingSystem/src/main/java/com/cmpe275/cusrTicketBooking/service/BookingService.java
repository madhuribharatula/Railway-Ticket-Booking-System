package com.cmpe275.cusrTicketBooking.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.cmpe275.cusrTicketBooking.Exceptions.CancellationTimeLimitException;
import com.cmpe275.cusrTicketBooking.Exceptions.SeatNotAvailableException;
import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.User;

public interface BookingService {

	public Booking bookTicket(String userId, String trainNumber, String journeyDate, String source, String destination,
			int ticketCount,boolean isRoundTrip) throws Exception;
	
	public boolean checkAvailability(Booking bookingDetails);

	public Booking bookRoundTripTicket(String userId, String outBoundtrainNumber, String outboundDate,
			String returntrainNumber, String returnDate, String source, String destination, int ticketCount, boolean b)throws Exception;

	public List<Booking> getCurrentBookings(Train train, Date journeyDate);

	public int getOverLapBookingCount(List<Booking> currentBookings, String source, String destination,
			String trainNumber);

	public List<Booking> bookTicketsWithAvailabilityCheck(User user, List<Itinerary> itineraryList,List<String> Passengernames) throws SeatNotAvailableException;
	
	public List<Booking> bookTickets(User user, List<Itinerary> itineraryList,List<String> Passengernames);
	
	public List<Booking> cancelTickets(String userId, String itineraryId) throws CancellationTimeLimitException;

	public List<List<Booking>> cancelItineraryForCancelledTrain(Train train, Date journeyDate);

	public List<Booking> rebookTickets(User user, List<String> passengerName, List<Itinerary> newItineraryList);
}

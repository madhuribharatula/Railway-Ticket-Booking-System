package com.cmpe275.cusrTicketBooking.repositories;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.BookingStatus;
import com.cmpe275.cusrTicketBooking.model.ItChecked;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainStatus;
import com.cmpe275.cusrTicketBooking.model.TrainTrip;
import com.cmpe275.cusrTicketBooking.model.User;

@Transactional
@Repository
public class BookingDaoJpaImpl implements BookingDao{

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Transactional
	public void bookTicket(Booking bookingRecord) {
		  entityManager.persist(bookingRecord);
	}

	@Transactional
	public List<Booking> readBookings(Train train, Date journeyDate,BookingStatus status) {
		Query query = entityManager.createNamedQuery("Booking.findActiveBookings");
		query.setParameter("train", train);
		query.setParameter("journeyDate", journeyDate);
		query.setParameter("bookingStatus", status);
		return query.getResultList();
	}

	@Transactional
	public void bookTicket(List<Booking> bookingList) {
		for(Booking book: bookingList) {
			  entityManager.persist(book);
		}
	}

	@Transactional
	public void updateItineray(List<Booking> bookingList, String itineraryId, double totalPrice) {
			for(Booking book: bookingList) {
			book.setItineraryId(itineraryId);
			book.setTotalPrice(totalPrice);
		}
	}

	@Transactional
	public List<Booking> cancelTickets(User user, String itineraryId) {
		
		
		Query query = entityManager.createQuery(
			      "UPDATE Booking b SET b.bookingStatus = :bookingStatus " +
			      "WHERE b.itineraryId = :itineraryId AND user = :user");
		query.setParameter("bookingStatus", BookingStatus.CANCELED);
		query.setParameter("itineraryId", itineraryId);
		query.setParameter("user", user);
		query.executeUpdate();
						
		return getBookingFromItinerary(user,itineraryId);

		
	}

	@Transactional
	public Map<ItChecked,List<Booking>> getALLBookingsForUser(User user) {
		Query getQuery = entityManager.createQuery(
			      "SELECT b.itineraryId FROM Booking b "+
			      "WHERE b.user = :user GROUP BY b.itineraryId");
		
		getQuery.setParameter("user", user);
		List<String> results = getQuery.getResultList();
		
		Map<ItChecked,List<Booking>> map = new HashMap<ItChecked,List<Booking>>();
		
		for(String itineraryId : results) {
			Query q = entityManager.createQuery("SELECT b FROM Booking b "+
			      "WHERE b.itineraryId = :itineraryId");
			
			q.setParameter("itineraryId", itineraryId);
			List<Booking> results1 = q.getResultList();
			ItChecked itchecked = new ItChecked();
			itchecked.setItenary(itineraryId);
			map.put(itchecked, results1);
		}
		return map;
			
	}
	@Transactional
	public List<Booking> getBookingFromItinerary(User user, String itineraryId) {
		Query getQuery = entityManager.createQuery(
			      "SELECT b FROM Booking b "+
			      "WHERE b.itineraryId = :itineraryId AND user = :user");
		
		getQuery.setParameter("itineraryId", itineraryId);
		getQuery.setParameter("user", user);
		List<Booking> results = getQuery.getResultList();
		return results;
	}

	@Transactional
	public List<Booking> getBookingsFor(Train train, Date journeyDate) {
		Query getQuery = entityManager.createQuery(
			      "SELECT b FROM Booking b "+
			      "WHERE b.train = :train AND b.journeyDate = :journeyDate");
		
		getQuery.setParameter("train", train);
		getQuery.setParameter("journeyDate", journeyDate);
		List<Booking> results = getQuery.getResultList();
		return results;
		
	}
	
	@Transactional
	public List<Booking> getBookingFromTraintrip(Train train,Date tripDate) {
		String sql = "SELECT b FROM Booking b "
				+ "  WHERE b.trainNumber = :trainNumber AND b.journeyDate = :tripDate";
		Query query = entityManager.createQuery(sql);
		query.setParameter("trainNumber", train.getTrainNumber());
		query.setParameter("tripDate", tripDate);
		return (List<Booking>) query.getResultList().get(0);
	}
	

	@Transactional
	public void resetBookings() {
		Query query = entityManager.createQuery("DELETE FROM Booking b ");
		query.executeUpdate();
	}


}

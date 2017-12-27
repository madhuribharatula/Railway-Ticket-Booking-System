package com.cmpe275.cusrTicketBooking.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.Exceptions.CancellationTimeLimitException;
import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.SearchOptions;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainTrip;
import com.cmpe275.cusrTicketBooking.model.TrainType;
import com.cmpe275.cusrTicketBooking.model.User;
import com.cmpe275.cusrTicketBooking.repositories.BookingDao;
import com.cmpe275.cusrTicketBooking.repositories.TrainDao;

@Service
@Transactional(readOnly = true)
public class TrainServiceImpl implements TrainService{
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private SearchService searchService;

	@Transactional
	public Train getTrainByNumber(String trainNumber) {
		return trainDao.readByNumber(trainNumber);
	}
	
	@Transactional
	public Train getTrain(int trainId) {
		return trainDao.read(trainId);
	}

	@Transactional
	public List<Train> getRunningTraindByType(TrainType type,Date journeyDate) {
		return trainDao.getRunningTraindByType(type,journeyDate);
	}

	@Transactional
	public void cancelTrain(String trainId, Date tripDate) throws CancellationTimeLimitException {
		LocalTime timeNow = LocalTime.now(ZoneId.of("America/Los_Angeles"));
		LocalTime timeCheck = timeNow.plusHours(3);
		TrainTrip traintrip =  trainDao.readByNumberAndTripDate(trainId,tripDate);
        LocalDate localDate = LocalDate.now();
		if(!timeCheck.isBefore(traintrip.getTrain().getStartTime().toLocalTime()) && (localDate.isEqual(tripDate.toLocalDate()) || localDate.isAfter(tripDate.toLocalDate()))) {
			throw new CancellationTimeLimitException();
		}
		
		trainDao.cancelTrainTrip(traintrip);
		rebookTickets(traintrip);
				
	}
	@Transactional
	public List<Booking> rebookTickets(TrainTrip trip) {
		
		Train train = trip.getTrain();
		Date journeyDate = trip.getTripDate();
		
		List<Booking> results = new ArrayList<Booking>();
		
		//Cancel the Itineraries that contains this train
		List<List<Booking>> bookingList = bookingService.cancelItineraryForCancelledTrain(train,journeyDate);
		
		//Search with the options and rebook 
		
		for(List<Booking> list: bookingList) {
			boolean hasExpress = false;
			boolean hasReturn = false;
			int connectionCount =0;
			User user = list.get(0).getUser();
			List<String> passengerName = list.get(0).getPassenger_list();
			List<Booking> returnTrip = new ArrayList<Booking>();
			for(Booking record : list) {
				if(record.getTrain().getTrainType().equals(TrainType.EXPRESS)) {
					hasExpress = true;
				}
				if(record.isReturnTrip()) {
					hasReturn = true;
					returnTrip.add(record);
				} else {
					connectionCount++;
				}
			}
			TrainType type = hasExpress? TrainType.EXPRESS : TrainType.REGULAR;
			list.removeAll(returnTrip);
			SearchOptions options = new SearchOptions();
			
			options.setPassengerCount(list.get(0).getTicketCount());
			options.setOutBoundJourneyDate(list.get(0).getJourneyDate());
			options.setExactTime(false);
			options.setToStation(list.get(list.size()-1).getDestStation().getStationName());
			options.setFromStation(list.get(0).getSourceStation().getStationName());
			options.setNumberOfConnections(connectionCount);
			options.setOutBoundDeptTime(list.get(0).getTrainDepartureTime().toString());
			options.setTrainType(type);
			options.setRoundTrip(hasReturn);
			if(hasReturn) {
				options.setReturnDeptTime(returnTrip.get(0).getTrainDepartureTime().toString());
				options.setReturnJourneyDate(returnTrip.get(0).getJourneyDate());
			}
			
			Map<TicketType,List<Itinerary>> searchMap = searchService.search(options);
			
			List<Itinerary> newItineraryList = new ArrayList<Itinerary>();
			for( List<Itinerary> searchList : searchMap.values() ) {
				if(!searchList.isEmpty())
					newItineraryList.add(searchList.get(0));
			}
			
			List<Booking> subBooking = bookingService.rebookTickets(user,passengerName,newItineraryList);
			results.addAll(subBooking);

		}
		return results;		
	}
}



package com.cmpe275.cusrTicketBooking.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.CusrConstants;
import com.cmpe275.cusrTicketBooking.Exceptions.TrainNotAvailableException;
import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.ConnectionType;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.ItineraryItem;
import com.cmpe275.cusrTicketBooking.model.Schedule;
import com.cmpe275.cusrTicketBooking.model.SearchOptions;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainType;
import com.cmpe275.cusrTicketBooking.repositories.TrainDao;

/*
 * Final Version of search
 */

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private BookingService bookingService;
	
	@Override
	public List<List<Schedule>> searchForTrains(SearchOptions options) throws Exception{
		
		if(options.isExactTime()) {
			List<Schedule> outBoundList = searchByExactTime(options,TicketType.ONEWAY);
			List<Schedule> returnList = searchByExactTime(options,TicketType.ROUNDTRIP);

		}
		if(options.getConnectionType().equals(ConnectionType.None)) {
			
		}
		return null;
	}
	
	private List<Schedule> searchByExactTime(SearchOptions options,TicketType type) throws Exception{
		
		// Get All the running trains on given Date of given type.
		Date journeyDate = type.equals(TicketType.ONEWAY)? options.getOutBoundJourneyDate():options.getReturnJourneyDate();
		String departTime =  type.equals(TicketType.ONEWAY)? options.getOutBoundDeptTime():options.getReturnDeptTime();
		
		List<Train> trainList = trainService.getRunningTraindByType(options.getTrainType(), journeyDate);
		
		if(trainList.isEmpty()) {
			throw new TrainNotAvailableException();
		}
		
		// when connections are not allowed 
		
		if(options.getConnectionType().equals(ConnectionType.None)) {
			
		}
		
		return null;
		
	}
	
	public Map<TicketType,List<Itinerary>> search(SearchOptions options) {
		Map<TicketType,List<Itinerary>> results = new HashMap<>();
		List<Itinerary> onewayItinerary = search(options,TicketType.ONEWAY);
		results.put(TicketType.ONEWAY,onewayItinerary);
		if(options.isRoundTrip()) {
			List<Itinerary> itinerary = search(options,TicketType.ROUNDTRIP);
			results.put(TicketType.ROUNDTRIP,itinerary);
		}
		return results;		
	}
	
	public List<Itinerary> search(SearchOptions options,TicketType type) {
		
		Date journeyDate = type.equals(TicketType.ONEWAY)? options.getOutBoundJourneyDate():options.getReturnJourneyDate();
		String departTime =  type.equals(TicketType.ONEWAY)? options.getOutBoundDeptTime():options.getReturnDeptTime();
		String source = type.equals(TicketType.ONEWAY)? options.getFromStation():options.getToStation();
		String destination = type.equals(TicketType.ONEWAY)? options.getToStation():options.getFromStation();
		TrainType trainType = options.getTrainType();
		
		int connecount = (options.getConnectionType() == null) ? options.getNumberOfConnections(): options.getConnectionType().getValue();
		
		if(trainType.equals(TrainType.REGULAR) && connecount == 2){
			connecount = 1;
		}

		
		String trainNumber = (source.charAt(0) - destination.charAt(0)) > 0 ?"NB%":"SB%";
		
		List<Itinerary> list = new ArrayList<Itinerary>();
		for (int i  = 0;  i <connecount ; i++) {
			List<ItineraryItem> res = new ArrayList<ItineraryItem>();
			getAllTrains(list,res,i,journeyDate,departTime,source,destination,trainType,options.isExactTime(),
					options.getPassengerCount(),0,trainNumber,true,null);
		}
		
		if(trainType.equals(TrainType.EXPRESS)) {
			removeAllRegularTrains(list);
		}
		sortItineraryByArrivalTime(list);
		List<Itinerary> subList = new ArrayList<>();
		List<Itinerary> result = new ArrayList<>();

		
		while(!list.isEmpty() && result.size() != 5) {
			subList = list.subList(0, 5);
			checkAvailability(subList);
			result.addAll(subList);
		}
		
		return result;
	}
	
private double getTicketPrice(ItineraryItem bookingRecord) {
		
		int stationsNumber = Math.abs(bookingRecord.getDestination().charAt(0) - bookingRecord.getSource().charAt(0));
		int quotient = stationsNumber/CusrConstants.noOfStops;
		int rem = stationsNumber%CusrConstants.noOfStops;
		double price = 0.0;
		if(TrainType.EXPRESS.equals(bookingRecord.getTrain().getTrainType())) {
			price = quotient*CusrConstants.expressFare;
			price = (rem >0)?price+CusrConstants.expressFare:price;
		} else {
			price = quotient*CusrConstants.regularFare;
			price = (rem >0)?price+CusrConstants.regularFare:price;
		}
		return price*bookingRecord.getTicketCount();
	}

	
	private void removeAllRegularTrains(List<Itinerary> list) {
		Iterator<Itinerary> iterator = list.iterator();
		while (iterator.hasNext()) {
			Itinerary itinerary = iterator.next();
			List<ItineraryItem> items = itinerary.getItineraryItemList();
			boolean isExpress = false;
			for(ItineraryItem item: items) {
				if(TrainType.EXPRESS.equals(item.getTrain().getTrainType()) ) {
					isExpress = true;
					break;
				}
			}
			if(!isExpress) {
				iterator.remove();
			}
		}
	}

	private void sortItineraryByArrivalTime(List<Itinerary> list) {
		Collections.sort(list ,new SortByArrivalTime());
		List<Itinerary> newList = list.subList(0, Math.min(5,list.size()));
		//list.retainAll(newList); // TODO CHnegd Now CHeck 
	}
	
	class SortByArrivalTime  implements Comparator<Itinerary> {
		
		
		
		public int compare(Itinerary a, Itinerary b)
	    {
	        return a.getItineraryItemList().get(a.getItineraryItemList().size()-1).getArrivalTime().compareTo(b.getItineraryItemList().get(b.getItineraryItemList().size()-1).getArrivalTime());
	    }
		
	}

	
	
	public void checkAvailability(List<Itinerary> list) {
		List<Itinerary> notAvailableIt = new ArrayList<Itinerary>();
		for(Itinerary itenerary : list) {
			List<ItineraryItem> itemList = itenerary.getItineraryItemList();
			double total = 0.0;
			for(ItineraryItem item:itemList) {
				
				int trainCapacity = item.getTrain().getCapacity();
				int ticketCount = item.getTicketCount();
				List<Booking> currentBookings = bookingService.getCurrentBookings(item.getTrain(),item.getJourneyDate());
				int bookingCount = bookingService.getOverLapBookingCount(currentBookings,item.getSource(),item.getDestination(),item.getTrain().getTrainNumber());
				if( (trainCapacity-bookingCount) >= ticketCount ) {
					double price = getTicketPrice(item);
					item.setPrice(price);
					total += price;;
				} else {
					notAvailableIt.add(itenerary);
					break;
				}
				
			}
			itenerary.setTotalPrice(total);
			
		}
		list.removeAll(notAvailableIt);
	}
	

	public void getAllTrains(List<Itinerary> itnearyList,
							 List<ItineraryItem> res ,
												int allowedConnections,
												Date journeyDate,
												String departTime,
												String source,
												String destination,
												TrainType trainType,
												boolean isExactTime, 
												int ticketCount,
												int currentStage,
												String trainNumber,
												boolean canConsider,Time beforeTime) {
		
		List<Train> trainList = null;
		
		isExactTime = (canConsider) ? isExactTime : false;
		if(currentStage < allowedConnections) {
			trainList =trainDao.getTrainsAtGivenSource(journeyDate,departTime,source,isExactTime,trainType,destination,beforeTime);
			for(ItineraryItem i: res) {
				trainList.remove(i.getTrain());
			}
			Iterator<Train> trainIt = trainList.iterator();
			while(trainIt.hasNext()) {
				Train train = trainIt.next();
				Time departureTime = null;
				Set<Schedule> set = new HashSet<Schedule>();
				for(Schedule sch: train.getSchedules()) {
					int sourceCount = sch.getStation().getStationName().charAt(0) - source.charAt(0);
					int destCount = sch.getStation().getStationName().charAt(0) - destination.charAt(0);
					int actualval = source.charAt(0)-destination.charAt(0);

					if(sch.getStation().getStationName().equals(source)) {
						departureTime = sch.getDepartureTime();
					} else if(trainType.equals(TrainType.REGULAR) && ((sourceCount<0 && destCount<0)|| (sourceCount>0&&destCount>0))) {
						
						//do nothing
					} else {
						set.add(sch);
					}
				}
				for(Schedule s: set) {
					String newSource = s.getStation().getStationName();
					Time arrivalTime = s.getArrivalTime();
					if(!newSource.equals(source) && !newSource.equals(destination) && departureTime.toLocalTime().isBefore(arrivalTime.toLocalTime())) {
							ItineraryItem item = new ItineraryItem(train,source,newSource,journeyDate,departureTime,arrivalTime,ticketCount);
							res.add(item);
							getAllTrains(itnearyList,res,allowedConnections,journeyDate,s.getDepartureTime().toString(),newSource,destination,trainType,false,ticketCount,currentStage+1,trainNumber,false,arrivalTime);
							res.remove(item);
						}
					}
				}
			}
		else if(currentStage == allowedConnections) {
			trainList = trainDao.getTrainsAtGivenSourceDestination(journeyDate,departTime,source,destination,isExactTime,trainType,beforeTime);
			for(ItineraryItem i: res) {
				trainList.remove(i.getTrain());
			}
			if((trainList == null || trainList.isEmpty()) && res.size()>0) {
				res.remove(res.size()-1);
			}
			for(Train train: trainList) {
				List<Schedule> sList = train.getSchedules();
				Time departureTime = null;
				Time arrivalTime = null;
				for(Schedule s: sList) {
					if(s.getStation().getStationName().equals(source)) {
						departureTime = s.getDepartureTime();
					} else if(s.getStation().getStationName().equals(destination)){
						arrivalTime = s.getArrivalTime();
					}
					if(arrivalTime !=null && departureTime !=null) {
						break;
					}
				}
				if(departureTime.toLocalTime().isBefore(arrivalTime.toLocalTime())) {
					ItineraryItem item = new ItineraryItem(train,source,destination,journeyDate,departureTime,arrivalTime,ticketCount);
					List<ItineraryItem> beforeList = new ArrayList<ItineraryItem>(res);
					beforeList.add(item);
					Itinerary x = new Itinerary();
					x.setItineraryItemList(beforeList);
					itnearyList.add(x);
				}
				
		}

	}
		
		
}
}

package com.cmpe275.cusrTicketBooking.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.CusrConstants;
import com.cmpe275.cusrTicketBooking.Utility;
import com.cmpe275.cusrTicketBooking.ActiveMQMail.EmailMsg;
import com.cmpe275.cusrTicketBooking.ActiveMQMail.MsgProducer;
import com.cmpe275.cusrTicketBooking.ActiveMQMail.Producer;
import com.cmpe275.cusrTicketBooking.Exceptions.CancellationTimeLimitException;
import com.cmpe275.cusrTicketBooking.Exceptions.SeatNotAvailableException;
import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.BookingStatus;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.ItineraryItem;
import com.cmpe275.cusrTicketBooking.model.Stations;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainType;
import com.cmpe275.cusrTicketBooking.model.User;
import com.cmpe275.cusrTicketBooking.repositories.BookingDao;
import com.cmpe275.cusrTicketBooking.repositories.TrainDao;
import com.cmpe275.cusrTicketBooking.repositories.UserDao;

import org.modelmapper.ModelMapper;


@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService{

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrainService trainService;
	
	@Autowired
    Producer producer;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private TrainDao trainDao;
	
	
	@Autowired
	private UserDao userDao;
	
	
	
	
		
	@Transactional
	public boolean checkAvailability(Booking bookingDetails) {
		int trainCapacity = bookingDetails.getTrain().getCapacity();
		List<Booking> currentBookings = getCurrentBookings(bookingDetails.getTrain(),bookingDetails.getJourneyDate());
		int bookingCount = getOverLapBookingCount(currentBookings,bookingDetails.getSourceStation().getStationName(),bookingDetails.getDestStation().getStationName(),bookingDetails.getTrain().getTrainNumber());
		if( (trainCapacity-bookingCount) >= bookingDetails.getTicketCount() ) {
			return true;
		}
		return false;
	}

	@Transactional
	public int getOverLapBookingCount(List<Booking> currentBookings, String source, String destination,String trainNumber) {
		if(currentBookings == null || currentBookings.size() ==0 ) {
			return 0;
		}
		int[] interval = {source.charAt(0),destination.charAt(0)};
		if(trainNumber.startsWith("SB")) {
			int count = 0;
			for(Booking book: currentBookings) {
				int[] curr = {book.getSourceStation().getStationName().charAt(0),book.getDestStation().getStationName().charAt(0)};
				if((curr[1] <= interval[0]) || ( curr[0] >= interval[1]) ){
					//do nothing
				} else {
					count = count+book.getTicketCount(); // if overlap add the ticket count to count.
				}
			}
			return count;
		} else {
			int count = 0;
			for(Booking book: currentBookings) {
				int[] curr = {book.getSourceStation().getStationName().charAt(0),book.getDestStation().getStationName().charAt(0)};
				if( (curr[0] <= interval[1]) || (curr[1] >= interval[0])) {
					//do nothing
				} else {
					count = count+book.getTicketCount();
				}
			}
			return count;
		}
	}

	@Transactional
	public List<Booking> getCurrentBookings(Train train, Date journeyDate) {
		return bookingDao.readBookings(train,journeyDate,BookingStatus.CONFIRMED);
	}

	
	
	
	@Transactional
	public Booking bookTicket(String userId, String trainNumber, String journeyDate, String source, String destination,
			int ticketCount,boolean isRoundTrip) throws Exception{
				
		User currentUser = userService.getUserByEmail(userId);
		Train train = trainService.getTrainByNumber(trainNumber);
		Stations dest = new Stations(destination);
		Stations sour = new Stations(source);
		Time trainTime = scheduleService.getTrainArrivalTime(train,sour);

		Booking bookingRecord = new Booking();
		bookingRecord.setTrain(train);
		bookingRecord.setUser(currentUser);
		bookingRecord.setDestStation(dest);
		bookingRecord.setSourceStation(sour);
		bookingRecord.setJourneyDate(Utility.StringtoDateConvertor(journeyDate));
		bookingRecord.setTicketCount(ticketCount);
		bookingRecord.setTrainDepartureTime(trainTime);
		bookingRecord.setReturnTrip(isRoundTrip);
		
		if(!checkAvailability(bookingRecord)) {
			throw new SeatNotAvailableException();
		} else {
			double price = getTicketPrice(bookingRecord)+ getTransactionPrice();
			bookingRecord.setPrice(price);
			bookingRecord.setBookingStatus(BookingStatus.CONFIRMED);
			bookingDao.bookTicket(bookingRecord);
		}
		return bookingRecord;
	}
	
	

	private double getTransactionPrice() {
		return CusrConstants.transactionRate;
	}

	@Transactional
	private double getTicketPrice(Booking bookingRecord) {
		
		int stationsNumber = Math.abs(bookingRecord.getDestStation().getStationName().charAt(0) - bookingRecord.getSourceStation().getStationName().charAt(0));
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

	@Transactional
	public Booking bookRoundTripTicket(String userId, String outBoundtrainNumber, String outboundDate,
			String returntrainNumber, String returnDate, String source, String destination, int ticketCount, boolean b)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Booking> bookTicketsWithAvailabilityCheck(User user, List<Itinerary> itineraryList,List<String> passengerNames) throws SeatNotAvailableException {
		if(!checkAvailability(itineraryList)) {
			throw new SeatNotAvailableException();
		}else {
			return bookTickets(user,itineraryList,passengerNames);
		}	
	}
	
	@Transactional
	public List<Booking> bookTickets(User user, List<Itinerary> itineraryList,List<String> passengerNames) {
		List<Booking> bookingList = new ArrayList<Booking>();
		int i = 0;
		for(Itinerary list : itineraryList) {
			boolean isReturn = (i == 0)? false : true; 
				for(ItineraryItem item : list.getItineraryItemList()) {
					Booking bookingRecord = convertItineraryItemToBooking(item,isReturn,user);
					bookingRecord.setPassenger_list(passengerNames);
					bookingList.add(bookingRecord);
				}
				i++;
		}
		bookingDao.bookTicket(bookingList);
		StringBuilder sb = new StringBuilder();
		sb.append("IT_");
		double totalPrice = 0;
		for(Booking book: bookingList) {
			sb.append(book.getBookingId());
			totalPrice += book.getPrice();
		}
		totalPrice += getTransactionPrice();
		bookingDao.updateItineray(bookingList,sb.toString(),totalPrice);
		return bookingList;
	}
	
		
	
	private Booking convertItineraryItemToBooking(ItineraryItem item,boolean isReturn,User user) {
		
		Booking bookingRecord = new Booking();
		bookingRecord.setTrain(item.getTrain());
		bookingRecord.setUser(user);
		bookingRecord.setDestStation(new Stations(item.getDestination()));
		bookingRecord.setSourceStation(new Stations(item.getSource()));
		bookingRecord.setJourneyDate(item.getJourneyDate());
		bookingRecord.setTicketCount(item.getTicketCount());
		bookingRecord.setTrainDepartureTime(item.getDepartureTime());
		bookingRecord.setReturnTrip(isReturn);
		double price = (item.getPrice() == 0.0)?getTicketPrice(bookingRecord):item.getPrice();
		bookingRecord.setPrice(price);
		bookingRecord.setBookingStatus(BookingStatus.CONFIRMED);

		// need to write logic for booked on date 
		
		return bookingRecord;
		
		
	}
	
	@Transactional
	public boolean checkAvailability(List<Itinerary> list) {
		for(Itinerary itenerary : list) {
			List<ItineraryItem> itemList = itenerary.getItineraryItemList();
			for(ItineraryItem item:itemList) {
				Train train = trainDao.read(item.getTrain().getTrainId());
				item.setTrain(train);

				int trainCapacity = item.getTrain().getCapacity();
				int ticketCount = item.getTicketCount();
				List<Booking> currentBookings = getCurrentBookings(item.getTrain(),item.getJourneyDate());
				int bookingCount = getOverLapBookingCount(currentBookings,item.getSource(),item.getDestination(),item.getTrain().getTrainNumber());
				if( (trainCapacity-bookingCount) >= ticketCount ) {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Transactional
	public List<Booking> cancelTickets(String userId, String itineraryId) throws CancellationTimeLimitException {
		User user = userDao.read(userId);
		if(!canCancel(user,itineraryId)) {
			throw new CancellationTimeLimitException();
		}
		return bookingDao.cancelTickets(user,itineraryId);
	}
	
	private boolean canCancel(User user, String itineraryId) {
		List<Booking> list = bookingDao.getBookingFromItinerary(user,itineraryId);
//		Date currentDate =  Utility.getCurrentDate();
//		String currentTime = Utility.getCurrentTime();
		LocalDate today = LocalDate.now( ); // TODO check the time zone to get current date in UTC for fair comparison.
		LocalTime timeNow = LocalTime.now();
		LocalTime timeCheck = timeNow.plusHours(1);

		for(Booking b: list) {
			
			LocalDate journeyDate = b.getJourneyDate().toLocalDate();
			LocalTime departTime = b.getTrainDepartureTime().toLocalTime();
			boolean validDate = journeyDate.isEqual( today ) || today.isBefore(journeyDate);
			boolean validTime = (journeyDate.isEqual( today ))?timeCheck.isBefore(departTime) : true;
			
			if(!validDate || !validTime) {
				return false;
			}
		}
		return true;
	}

	@Transactional
	public List<List<Booking>> cancelItineraryForCancelledTrain(Train train, Date journeyDate) {
	
		List<Booking> needToChange = bookingDao.getBookingsFor(train,journeyDate);
		List<List<Booking>> results = new ArrayList<>();
		for(Booking record: needToChange) {
			List<Booking> subList= bookingDao.cancelTickets(record.getUser(),record.getItineraryId());
			results.add(subList);
		}
		return results;
	}

	@Transactional
	public List<Booking> rebookTickets(User user, List<String> passengerName, List<Itinerary> newItineraryList) {
		List<Booking> bookingList = new ArrayList<Booking>();
		int i = 0;
		for(Itinerary list : newItineraryList) {
			boolean isReturn = (i == 0)? false : true; 
				for(ItineraryItem item : list.getItineraryItemList()) {
					Booking bookingRecord = convertItineraryItemToBooking(item,isReturn,user);
					bookingRecord.setPassenger_list(passengerName);
					bookingList.add(bookingRecord);
				}
				i++;
		}
		bookingDao.bookTicket(bookingList);
		StringBuilder sb = new StringBuilder();
		sb.append("IT_");
		double totalPrice = 0;
		for(Booking book: bookingList) {
			sb.append(book.getBookingId());
			totalPrice += book.getPrice();
		}
		totalPrice += getTransactionPrice();
		bookingDao.updateItineray(bookingList,sb.toString(),totalPrice);
		
		// TODO -  Add code for booking confirmation email
		EmailMsg emailMsg = new EmailMsg();
		emailMsg.setMailId(user.getEmailid());
		emailMsg.setBody(bookingList.toString());
		emailMsg.setSubject(Utility.trainCancellation);
		producer.setEmailMsg(emailMsg);
		this.producer.send(emailMsg);		
		// Email code ends
		return bookingList;
	}

	
	
}

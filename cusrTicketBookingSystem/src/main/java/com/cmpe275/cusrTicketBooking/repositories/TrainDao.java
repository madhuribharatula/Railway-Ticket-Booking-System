package com.cmpe275.cusrTicketBooking.repositories;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainTrip;
import com.cmpe275.cusrTicketBooking.model.TrainType;

public interface TrainDao {

	Train readByNumber(String trainNumber);

	List<Train> getRunningTraindByType(TrainType type,Date journeyDate);

	List<Train> getTrainsAtGivenSource(Date journeyDate, String departTime, String source, 
			boolean isExactTime, TrainType trainType,String destination,Time beforeTime);

	List<Train> getTrainsAtGivenSourceDestination(Date journeyDate, String departTime, String source, String destination,
			boolean isExactTime,TrainType trainType,Time beforeTime);

	Train read(int trainId);
	
	TrainTrip readByNumberAndTripDate(String trainNumber,Date tripDate);
	
	void cancelTrainTrip(TrainTrip trip);

	void resetCapacity(int trainCapacity);

}

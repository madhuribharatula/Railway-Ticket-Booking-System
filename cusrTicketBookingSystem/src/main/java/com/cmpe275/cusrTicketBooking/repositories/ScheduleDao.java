package com.cmpe275.cusrTicketBooking.repositories;

import java.sql.Time;

import com.cmpe275.cusrTicketBooking.model.Stations;
import com.cmpe275.cusrTicketBooking.model.Train;

public interface ScheduleDao {
	public Time getTrainArrivalTime(Train train, Stations source);

}

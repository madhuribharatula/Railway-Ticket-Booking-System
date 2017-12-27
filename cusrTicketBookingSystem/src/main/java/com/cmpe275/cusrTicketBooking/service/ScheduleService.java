package com.cmpe275.cusrTicketBooking.service;

import java.sql.Time;

import com.cmpe275.cusrTicketBooking.model.Stations;
import com.cmpe275.cusrTicketBooking.model.Train;

public interface ScheduleService {
	Time getTrainArrivalTime(Train train,Stations source);
}

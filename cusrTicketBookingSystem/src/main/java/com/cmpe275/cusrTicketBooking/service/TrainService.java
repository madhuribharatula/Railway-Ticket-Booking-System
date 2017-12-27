package com.cmpe275.cusrTicketBooking.service;

import java.sql.Date;
import java.util.List;

import com.cmpe275.cusrTicketBooking.Exceptions.CancellationTimeLimitException;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainType;

public interface TrainService {

	Train getTrainByNumber(String trainNumber);
	List<Train> getRunningTraindByType(TrainType type,Date journeyDate);
	void cancelTrain(String trainId, Date tripDate) throws CancellationTimeLimitException;
	
}

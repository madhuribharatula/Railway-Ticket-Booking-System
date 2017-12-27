package com.cmpe275.cusrTicketBooking.service;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.Stations;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.repositories.ScheduleDao;

@Service
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Transactional
	public Time getTrainArrivalTime(Train train, Stations source) {
		return scheduleDao.getTrainArrivalTime(train,source);
	}

}

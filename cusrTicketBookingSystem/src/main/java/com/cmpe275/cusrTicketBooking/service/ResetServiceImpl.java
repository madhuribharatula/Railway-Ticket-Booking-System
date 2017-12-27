package com.cmpe275.cusrTicketBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.ActiveMQMail.Producer;
import com.cmpe275.cusrTicketBooking.repositories.BookingDao;
import com.cmpe275.cusrTicketBooking.repositories.TrainDao;

@Service
@Transactional(readOnly = true)
public class ResetServiceImpl implements ResetService{
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Transactional
	public void resetSystem(int trainCapacity) {
		bookingDao.resetBookings();
		trainDao.resetCapacity(trainCapacity);	
	}

}

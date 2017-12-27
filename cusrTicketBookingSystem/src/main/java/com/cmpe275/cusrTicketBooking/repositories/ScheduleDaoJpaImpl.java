package com.cmpe275.cusrTicketBooking.repositories;

import java.sql.Time;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.Schedule;
import com.cmpe275.cusrTicketBooking.model.Stations;
import com.cmpe275.cusrTicketBooking.model.Train;

@Transactional
@Repository
public class ScheduleDaoJpaImpl implements ScheduleDao{

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Transactional
	public Time getTrainArrivalTime(Train train, Stations source) {
		Query query = entityManager.createNamedQuery("Schedule.getArrivalTime");
		query.setParameter("train", train);
		query.setParameter("station", source);
		Schedule schedule = (Schedule) query.getResultList().get(0);
		return schedule.getArrivalTime();
	}
	

}

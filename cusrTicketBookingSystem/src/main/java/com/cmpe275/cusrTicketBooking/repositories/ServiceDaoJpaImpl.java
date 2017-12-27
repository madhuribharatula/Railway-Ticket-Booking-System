package com.cmpe275.cusrTicketBooking.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.Schedule;
import com.cmpe275.cusrTicketBooking.model.SearchOptions;
import com.cmpe275.cusrTicketBooking.model.TicketType;

@Transactional
@Repository

public class ServiceDaoJpaImpl implements ServiceDao{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Transactional
	public List<Schedule> getSouthBoundwithNoConnection(SearchOptions options,TicketType type) {
		Query query = entityManager.createQuery("");
		return query.getResultList();
	
	}

}

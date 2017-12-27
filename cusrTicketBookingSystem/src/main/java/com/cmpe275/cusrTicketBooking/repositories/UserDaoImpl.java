package com.cmpe275.cusrTicketBooking.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.User;


@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Transactional
	public User read(String email) {
		User user = entityManager.find(User.class, email);
		return user;
	}
	@Transactional
	public void createUser(User user) {
		  entityManager.persist(user);
	  }
   
}

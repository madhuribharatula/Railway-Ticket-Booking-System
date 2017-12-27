package com.cmpe275.cusrTicketBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.User;
import com.cmpe275.cusrTicketBooking.repositories.TrainDao;
import com.cmpe275.cusrTicketBooking.repositories.UserDao;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	
	@Transactional
	public User save(User user) {
		userDao.createUser(user);
		return user;
	}
	@Transactional
	public User getUserByEmail(String email)
	{
		return userDao.read(email);
		
	}
}

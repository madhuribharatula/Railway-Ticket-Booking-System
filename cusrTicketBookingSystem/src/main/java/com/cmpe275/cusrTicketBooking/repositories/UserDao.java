package com.cmpe275.cusrTicketBooking.repositories;

import com.cmpe275.cusrTicketBooking.model.User;

public interface UserDao {

	User read(String email);

	void createUser(User user);

	

}

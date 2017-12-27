package com.cmpe275.cusrTicketBooking.service;

import com.cmpe275.cusrTicketBooking.model.User;

public interface UserService {

	public User save(User user);

	public User getUserByEmail(String email);

}

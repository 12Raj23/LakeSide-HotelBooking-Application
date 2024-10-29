package com.daily.codework.lakeSide_hotel.service;

import java.util.List;

import com.daily.codework.lakeSide_hotel.model.User;

public interface IUserService {
	
	User registerUser(User user);
	List<User>getUsers();
	void deleteUser(String email);
	User getUser(String email);

}

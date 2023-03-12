package com.perscholas.CardAdvantage.service;

import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.dto.RegistrationDto;

public interface UserService {
	void saveUser(RegistrationDto registrationDto);

	User findByEmail(String email);
	
	User findByUsername(String userName);
}

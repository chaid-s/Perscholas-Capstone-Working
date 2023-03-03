package com.perscholas.CardAdvantage.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.perscholas.CardAdvantage.dto.RegistrationDto;
import com.perscholas.CardAdvantage.entities.Role;
import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.repository.RoleRepository;
import com.perscholas.CardAdvantage.repository.UserRepository;
import com.perscholas.CardAdvantage.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setUName(registrationDto.getUName());
		user.setEmail(registrationDto.getEmail());
		//Do password encryption after security is properly implemented
		user.setPassword(registrationDto.getPassword());
		Role role = roleRepository.findByName("ROLE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	

}

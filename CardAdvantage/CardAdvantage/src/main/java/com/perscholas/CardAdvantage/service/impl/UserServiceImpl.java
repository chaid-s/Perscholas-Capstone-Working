package com.perscholas.CardAdvantage.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setUName(registrationDto.getUName());
		user.setEmail(registrationDto.getEmail());
		//Do password encryption after security is properly implemented
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		Role role = roleRepository.findByName("ROLE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByUsername(String userName) {
		return userRepository.findByuName(userName);
	}
	
	

}

package com.perscholas.CardAdvantage.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByuName(username);
		
		if(user != null) {
			org.springframework.security.core.userdetails.User authenticatedUser =
					new org.springframework.security.core.userdetails.User(
					user.getEmail(),
					user.getPassword(),
					user.getRoles().stream()
								.map((role)-> new SimpleGrantedAuthority(role.getName()))
								.collect(Collectors.toList())
					);
			
			return authenticatedUser;
		}else {
			throw new UsernameNotFoundException("Invalid username and password");
		}
				
	}

}

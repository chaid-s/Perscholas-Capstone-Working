package com.perscholas.CardAdvantage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perscholas.CardAdvantage.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String Email);
	
	User findByuName(String uName);
}

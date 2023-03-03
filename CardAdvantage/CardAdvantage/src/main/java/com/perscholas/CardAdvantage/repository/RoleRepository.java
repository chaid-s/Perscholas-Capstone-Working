package com.perscholas.CardAdvantage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perscholas.CardAdvantage.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}

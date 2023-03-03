package com.perscholas.CardAdvantage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.entities.UserProduct;

public interface UserProductRepository extends JpaRepository<UserProduct, Long>{
	
	Optional<UserProduct> findByUsersId(Long id);
}

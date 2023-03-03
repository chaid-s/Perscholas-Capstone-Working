package com.perscholas.CardAdvantage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perscholas.CardAdvantage.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findById(Long id);
	Optional<Product> findByName(String Name);

	
}

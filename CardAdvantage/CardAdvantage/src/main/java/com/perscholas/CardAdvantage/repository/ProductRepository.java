package com.perscholas.CardAdvantage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.perscholas.CardAdvantage.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findById(Long id);
	
	Optional<Product> findByName(String Name);
	
	@Query("Select p from Product p Where "+ 
	"p.name LIKE CONCAT('%', :query, '%') OR " +
	"p.description LIKE CONCAT('%', :query, '%')")
	List<Product> searchProducts(String query);
}

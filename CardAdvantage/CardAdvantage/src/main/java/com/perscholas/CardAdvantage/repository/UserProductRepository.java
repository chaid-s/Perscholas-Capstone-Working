package com.perscholas.CardAdvantage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.perscholas.CardAdvantage.entities.Product;
import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.entities.UserProduct;

public interface UserProductRepository extends JpaRepository<UserProduct, Long>{
	
	Optional<UserProduct> findById(Long id);
	
	@Query("Select p from UserProduct p Where "+ 
	"p.inCart = false and p.purchased = false")
	List<UserProduct> findActiveProducts();

	@Query("Select p from UserProduct p Where "+ 
	"p.inCart = true AND p.purchased = false")
	List<UserProduct> findCartProducts();
}

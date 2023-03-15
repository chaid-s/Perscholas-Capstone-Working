package com.perscholas.CardAdvantage.service;

import java.util.List;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.dto.UserProductDto;

public interface UserProductService {
	
	List<UserProductDto> findAllUserProducts();
	
	void createUserProduct(UserProductDto userProductDto);
	
	void deleteUserProduct(Long userProductId);
	
	UserProductDto findUserProductById(Long productId);

	void updateUserProduct(UserProductDto userProduct);
	
	List<UserProductDto> findAllCartProducts();
	
	List<UserProductDto> findAllActiveProducts();
	
	void cartUserProduct(Long userProductId);
	
	void uncartUserProduct(Long userProductId);
	
	void purchaseUserProduct(Long userProductId);
	
	void purchaseCart();

}

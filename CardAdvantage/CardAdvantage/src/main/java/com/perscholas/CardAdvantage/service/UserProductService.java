package com.perscholas.CardAdvantage.service;

import java.util.List;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.dto.UserProductDto;

public interface UserProductService {
	
	List<UserProductDto> findAllUserProducts();
	
	void createUserProduct(UserProductDto userProductDto);

}

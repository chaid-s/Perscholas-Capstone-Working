package com.perscholas.CardAdvantage.service;

import java.util.List;

import com.perscholas.CardAdvantage.dto.UserProductDto;

public interface UserProductService {
	
	List<UserProductDto> findAllUserProducts();

}

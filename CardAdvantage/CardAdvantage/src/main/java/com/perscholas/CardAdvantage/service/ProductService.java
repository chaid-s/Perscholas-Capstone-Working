package com.perscholas.CardAdvantage.service;

import java.util.List;

import com.perscholas.CardAdvantage.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> findAllProducts();
	
}

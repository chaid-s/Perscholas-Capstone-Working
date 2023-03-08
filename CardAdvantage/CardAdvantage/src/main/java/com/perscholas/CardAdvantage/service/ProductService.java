package com.perscholas.CardAdvantage.service;

import java.util.List;

import com.perscholas.CardAdvantage.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> findAllProducts();
	
	void createProduct(ProductDto productDto);
	
	ProductDto findProductById(Long productId);
	
	void updateProduct(ProductDto productDto);
	
	void deleteProduct(Long productId);
}

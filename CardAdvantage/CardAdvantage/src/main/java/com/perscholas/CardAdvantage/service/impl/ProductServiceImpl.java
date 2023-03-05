package com.perscholas.CardAdvantage.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.entities.Product;
import com.perscholas.CardAdvantage.mapper.ProductMapper;
import com.perscholas.CardAdvantage.repository.ProductRepository;
import com.perscholas.CardAdvantage.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<ProductDto> findAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductMapper::mapToProductDto)
				.collect(Collectors.toList());
	}

}

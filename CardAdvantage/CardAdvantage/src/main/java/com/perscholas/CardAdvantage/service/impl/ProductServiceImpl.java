package com.perscholas.CardAdvantage.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public void createProduct(ProductDto productDto) {
		Product product = ProductMapper.mapToProduct(productDto);
		productRepository.save(product);
	}

	@Override
	public ProductDto findProductById(Long productId) {
		Product product = productRepository.findById(productId).get();
		return ProductMapper.mapToProductDto(product);
	}

	@Override
	public void updateProduct(ProductDto productDto) {
		Product product = ProductMapper.mapToProduct(productDto);
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public List<ProductDto> searchProducts(String query) {
		List<Product> products= productRepository.searchProducts(query);
		return products.stream()
					.map(ProductMapper::mapToProductDto)
					.collect(Collectors.toList());
	}
	
	

}

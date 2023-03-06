package com.perscholas.CardAdvantage.mapper;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.entities.Product;

public class ProductMapper {
	
	public static ProductDto mapToProductDto(Product product) {
		return ProductDto.builder()
			   .id(product.getId())
			   .name(product.getName())
			   .description(product.getDescription())
			   .productPicture(product.getProductPicture())
			   .build();
		
	}
	
	public static Product mapToProduct(ProductDto productDto) 
	{
		return Product.builder()
				.id(productDto.getId())
				.name(productDto.getName())
				.description(null)
				.productPicture(productDto.getProductPicture())
				.build();
	}

}

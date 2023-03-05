package com.perscholas.CardAdvantage.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
	
	private Long id;
	
	@NotEmpty(message ="Product Name is Required")
	private String name;
	
	private byte[] productPicture;

}

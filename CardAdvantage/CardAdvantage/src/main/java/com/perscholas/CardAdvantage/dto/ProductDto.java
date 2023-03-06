package com.perscholas.CardAdvantage.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Long id;
	
	@NotEmpty(message ="Product Name is Required")
	private String name;
	
	private String description;
	
	private byte[] productPicture;

}

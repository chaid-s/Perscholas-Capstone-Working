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
public class UserProductDto {
	
	private Long id;
	
	@NotEmpty(message ="Listing Name is Required")
	private String name;
	
	@NotEmpty(message ="Listing Price is Required")
	private double price;
	
	@NotEmpty(message ="Listing Description is Required")
	private String description;
	
	private String verificationPicture;

}

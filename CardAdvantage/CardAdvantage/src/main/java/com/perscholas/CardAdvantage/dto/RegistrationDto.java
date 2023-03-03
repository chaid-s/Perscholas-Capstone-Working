package com.perscholas.CardAdvantage.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
	private int id;
	
	@NotEmpty(message ="Username is Required")
	private String uName;
	
	@Email
	@NotEmpty(message ="Email is Required")
	private String Email;
	
	@NotEmpty(message ="Password is Required")
	private String password;

}

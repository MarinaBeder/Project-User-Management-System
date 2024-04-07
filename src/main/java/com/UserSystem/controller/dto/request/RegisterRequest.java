package com.UserSystem.controller.dto.request;


import com.UserSystem.domain.Address;
import com.UserSystem.domain.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

	
	private String fullName;
	@NotBlank(message = "Email must be a valid email address")
	 @NotNull(message = "Email cannot be null")
	    @Email(message = "Email must be a valid email address")
	private String email;
	private String password;
    
	    @Embedded
	    private Address address;
	    @Min(value = 18, message = "Age must be greater than or equal to 18")
	    private Integer age;
	    @Enumerated(EnumType.STRING)
	    private Gender gender;
}

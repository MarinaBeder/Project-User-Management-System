package com.UserSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserSystem.controller.dto.request.RegisterRequest;
import com.UserSystem.controller.dto.response.UserResponse;
import com.UserSystem.service.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class RegisterController {
	   private final RegisterService registerService;

	   
		public RegisterController(RegisterService registerService) {
		this.registerService = registerService;
	}





		@PostMapping("/register")
	    public ResponseEntity<UserResponse> createUser( @Valid  @RequestBody RegisterRequest user) {
	        UserResponse createdUser = registerService.createUser(user);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	    }
}

package com.UserSystem.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;




@Data
public class AuthenticationResponse {

	
@JsonProperty("access_token")
	private String accessToken;
@JsonProperty("refresh_token")
private String refreshToken;

	
	
}

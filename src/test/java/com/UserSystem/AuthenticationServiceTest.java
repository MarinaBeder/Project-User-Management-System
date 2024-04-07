package com.UserSystem;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.UserSystem.controller.dto.request.AuthenticationRequest;

public class AuthenticationServiceTest {

	
	
	   @Test
	    public void testValidUser() {
	        // Test validating a user with valid credentials
	        given()
	            .contentType("application/json")
	            .body("{\"email\": \"marinabeder98@gmail.com\", \"password\": \"123456\"}")
	        .when()
	        .post("/api/v1/user/authenticate")
	        .then()
	            .statusCode(200);
	    }
	
@Test
public void testInvalidUser() {
    // Test validating a user with valid credentials
    given()
        
        .contentType("application/json")
        .body("{\"email\": \"test\", \"password\": \"password\"}")
    .when()
        .post("/api/v1/user/authenticate")
    .then()
        .statusCode(401);
        
}



}
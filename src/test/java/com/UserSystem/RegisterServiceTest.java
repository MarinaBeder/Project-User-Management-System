package com.UserSystem;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class RegisterServiceTest {
   

	/*
	 ** note**
	
	 the first test case ( test Create User ) will give success for the first time 
	 but if I run it again it fails
	 because the email should be unique 
     because when a user registers we need something to be unique for login
     so the user should enter (his/her) email and the email should be unique
	*/
	 @Test
	    public void testCreateUser() {
	        // Request body
	        String requestBody = "{"
	            + "\"fullName\": \"Marina Beder\","
	            + "\"email\": \"marinabeder98@gmail.com\","
	            + "\"password\": \"123456\","
	            + "\"address\": {"
	                + "\"streetNo\": \"1\","
	                + "\"neighbourhood\": \"cairo\","
	                + "\"city\": \"Cairo\","
	                + "\"country\": \"Egypt\""
	            + "},"
	            + "\"age\": 18,"
	            + "\"gender\": \"FEMALE\""
	        + "}";

	        // Test creating a user
	        given()
	            .contentType("application/json")
	            .body(requestBody)
	        .when()
	            .post("/api/v1/user/register")
	        .then()
	            .statusCode(201); //the status code for successful user creation is 201
	    }	
	
	 @Test
	    public void testCreateUserInvaildEmail() {
	        // Request body
	        String requestBody = "{"
	            + "\"fullName\": \"Marina Beder\","
	            + "\"email\": \"marinabeder98\","
	            + "\"password\": \"123456\","
	            + "\"address\": {"
	                + "\"streetNo\": \"1\","
	                + "\"neighbourhood\": \"cairo\","
	                + "\"city\": \"Cairo\","
	                + "\"country\": \"Egypt\""
	            + "},"
	            + "\"age\": 18,"
	            + "\"gender\": \"FEMALE\""
	        + "}";

	        // Test creating a user
	        given()
	            .contentType("application/json")
	            .body(requestBody)
	        .when()
	            .post("/api/v1/user/register")
	        .then()
	            .statusCode(400) //the status code for 400 because of invaild email
	            .body("email",equalTo("Email must be a valid email address"));
	            
	    }	
	
	 
	 @Test
	    public void testCreateUserInvalidAge() {
	        // Request body
	        String requestBody = "{"
	            + "\"fullName\": \"Marina Beder\","
	            + "\"email\": \"marinabeder98@gmail.com\","
	            + "\"password\": \"123456\","
	            + "\"address\": {"
	                + "\"streetNo\": \"1\","
	                + "\"neighbourhood\": \"cairo\","
	                + "\"city\": \"Cairo\","
	                + "\"country\": \"Egypt\""
	            + "},"
	            + "\"age\": 1,"
	            + "\"gender\": \"FEMALE\""
	        + "}";

	        // Test creating a user
	        given()
	            .contentType("application/json")
	            .body(requestBody)
	        .when()
	            .post("/api/v1/user/register")
	        .then()
	            .statusCode(400) //  the status code for will be 400 because age less than 18
	            .body("age",equalTo("Age must be greater than or equal to 18"));
	    }	
	 
	 
	 
	 
	  
	  
	  /*
	   * 
	  @Test
	    public void testCreateUserDuplicateEmail() {
	        // Request body
	        String requestBody = "{"
	            + "\"fullName\": \"Marina Beder\","
	            + "\"email\": \"marinabeder98@gmail.com\","
	            + "\"password\": \"123456\","
	            + "\"address\": {"
	                + "\"streetNo\": \"1\","
	                + "\"neighbourhood\": \"cairo\","
	                + "\"city\": \"Cairo\","
	                + "\"country\": \"Egypt\""
	            + "},"
	            + "\"age\": 18,"
	            + "\"gender\": \"FEMALE\""
	        + "}";

	        // Test creating a user
	        given()
	            .contentType("application/json")
	            .body(requestBody)
	        .when()
	            .post("/api/v1/user/register")
	        .then()
	            .statusCode(409) // Assuming the status code of duplicate email is 409
	            .body("message",equalTo("Email is already Exist"));
	    }
	    	
	  */
	  
	  
	  
	  
	  
	 
	 
}

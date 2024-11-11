package api.endpoints;

import org.testng.annotations.Test;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//created for to perform CRUD operations

public class UserEndPoints {
	//Response is the return type for the return response
	
	public static Response createUser (User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return response;
		
		
	}
   
	
	public static Response readUser (String userName)
	{
		//Response is variable type
		Response response = given()
				.pathParam("username", userName)
		
		.when()
		.get(Routes.get_url);
		
		return response;
		
		
	}
	
	public static Response updateUser (User payload,String userName)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		
		.when()
		.put(Routes.update_url);
		
		return response;
		
		
	}
	
	public static Response deleteUser (String userName)
	{
		Response response = given()
				.pathParam("username", userName)
		
		.when()
		.delete(Routes.delete_url);
		
		return response;
		
		
	}
	
	
}
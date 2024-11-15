package api.endpoints;

import org.testng.annotations.Test;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

//created for to perform CRUD operations

public class UserEndPoints2 {
	
	// method created for getting URL's from properties file
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load the properties file ( it automatically load the location of the routes.properties file 
		return routes;
	}
	public static Response createUser (User payload)
	{
		String post_url = getURL().getString("post_url"); //by getString method by passing the key we will get the URL
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
		.post(post_url);
		
		return response;
		
		
	}
   
	
	public static Response readUser (String userName)
	{
		//Response is variable type
		String get_url = getURL().getString("get_url"); 
		Response response = given()
				.pathParam("username", userName)
		
		.when()
		.get(get_url);
		
		return response;
		
		
	}
	
	public static Response updateUser (User payload,String userName)
	{
		String update_url = getURL().getString("update_url"); 
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		
		.when()
		.put(update_url);
		
		return response;
		
		
	}
	
	public static Response deleteUser (String userName)
	{
		String delete_url = getURL().getString("delete_url"); 
		Response response = given()
				.pathParam("username", userName)
		
		.when()
		.delete(delete_url);
		
		return response;
		
		
	}
	
	
}

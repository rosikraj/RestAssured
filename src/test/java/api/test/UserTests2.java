package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker; // its a variable //to genarate the fake data after genarating we need to pass to pojo then hit the api
	User userPayload; //variable to pass the data for pojo
	
	public Logger logger;
	
	@BeforeClass //its an annotation in testng , this particular methods will execute before starting all the test methods
	public void setupData()
	{
		
		faker = new Faker(); //faker object we need to create 
		userPayload = new User(); //object we will pass the data to it
		
		userPayload.setId(faker.idNumber().hashCode());  //hashCode means it genarate randomly everytime
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(8, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs ( we have some pre-defined class logmanager
		logger = LogManager.getLogger(this.getClass()); //
		
		
		
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("********* Create User ************");
		Response response = UserEndPoints2.createUser(userPayload); //store the api call in response variable
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{
		logger.info("********* Read User ************");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername()); 
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		//update data using payload
		logger.info("********* Update User ************");
		userPayload.setFirstname(faker.name().firstName()); //when we use/call same statements here it will genarate new data here
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response =UserEndPoints2.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//checking data after update
		Response responseAfterUpdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
		logger.info("********* Delete User ************");
		Response response =UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().body().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("Successfully deleted the User");
	}
}

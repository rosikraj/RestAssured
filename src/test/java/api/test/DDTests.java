package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String userName, String fName, String lname,String useremail,String pwd, String ph) //by using dataprovider we get this data
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setFirstname(fName);
		userPayload.setLastname(lname);
		userPayload.setUsername(userName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload); 
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		 Response response=UserEndPoints.deleteUser(userName);
		 Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}

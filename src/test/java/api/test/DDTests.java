package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
    @Test(priority = 1,dataProvider="data",dataProviderClass = DataProviders.class)
	public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph) 
    {
		User payload=new User();
		payload.setId(Integer.parseInt(userID));
		payload.setFirstname(lname);
		payload.setLastname(lname);
		payload.setEmail(useremail);
		payload.setPassword(pwd);
		payload.setPhone(ph);
		
		  Response response= UserEndPoints.createUser(payload);
		  Assert.assertEquals(response.getStatusCode(),200);
	}
    @Test(priority = 2,dataProvider="UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) 
    {
    //testPostuser(userName, userName, userName, userName, userName, userName, userName);
        
    	Response response=UserEndPoints.deleteUser(userName);
    	//System.out.println(response);
    	//Assert.assertEquals(response.getStatusCode(),200);
    	//System.out.println(userName);
    }
    
    
}

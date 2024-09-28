package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
   Faker faker;
   User userPayload;
   public Logger logger;//for logs
   
   @BeforeMethod
   public void setup()
   {
	   faker=new Faker();
	   userPayload=new User();
	   
	   userPayload.setId(faker.idNumber().hashCode());
	   userPayload.setUsername(faker.name().username());
	   userPayload.setFirstname(faker.name().firstName());
	   userPayload.setLastname(faker.name().lastName());
	   userPayload.setEmail(faker.internet().safeEmailAddress());
	   userPayload.setPassword(faker.internet().password(5,10));
	   userPayload.setPhone(faker.phoneNumber().cellPhone());
	   
	   //logs
	   logger=LogManager.getLogger(this.getClass());
   }
   @Test(priority=1)
   public void testPostUser()
   {
	   logger.info("*********Creating user**********");
	  Response response= UserEndPoints.createUser(userPayload);
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(),200);
	  logger.info("*********User is created**********");
	  //System.out.println("User created with username: " + userPayload.getUsername());
   }
   @Test(priority = 2)
   public void testGetUserByName() 
   {
	   logger.info("*********Reading user info**********");
	  // testPostUser(); // Ensure user is created before fetching
	 Response response=UserEndPoints.readUser(this.userPayload.getUsername());
	 response.then().log().all();
	// Assert.assertEquals(response.getStatusCode(),200);
	 logger.info("*********user info is displayed**********");
	 //System.out.println("Fetching user: " + userPayload.getUsername());
   }
   @Test(priority=3)
   public void testUpdateUserByName()
   {
	   //update data using payload
	   logger.info("*********update user**********");
	   userPayload.setFirstname(faker.name().firstName());
	   userPayload.setLastname(faker.name().lastName());
	   userPayload.setEmail(faker.internet().safeEmailAddress());
	   
	   Response response=UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		 response.then().log().body();
		// Assert.assertEquals(response.getStatusCode(),200);
		 logger.info("*********user is updated**********");
		 //checking data after update
		 Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
		 Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
   }
   @Test(priority=4)
   public void testDeleteUserByName()
   {
	   logger.info("*********deleting user**********");
	   //testPostUser();
	   Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
	  // Assert.assertEquals(response.getStatusCode(),200);
	   logger.info("*********deleted user**********");
   }

   }
 
package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.jayway.jsonpath.internal.Path;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndPoints.java
//created for perform Create,Read,Update,Delete requests to the user API
public class UserEndPoints {
    //Create User method
	public static Response createUser(User payload)
	{
		
		Response response =given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		.when()
		  .post(Routes.post_url);
		  return response;
	}
	//get usser method
	public static Response readUser(String userName)
	{
		
		Response response =given()
		   .pathParam("username", userName)
		.when()
		  .get(Routes.get_url);
		  return response;
	}
	//update user method
	public static Response updateUser(User payload,String userName)
	{
		
		Response response =given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		   .pathParam("username", userName)
		.when()
		  .put(Routes.update_url);
		  return response;
	}
	//delete user method
	public static Response deleteUser(String userName)
	{
		
		Response response =given()
		   .pathParam("username", userName)
		.when()
		  .delete(Routes.delete_url);
		  return response;
	}
	
	
}

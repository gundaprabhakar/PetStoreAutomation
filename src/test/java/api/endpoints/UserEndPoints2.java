package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import com.jayway.jsonpath.internal.Path;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndPoints.java
//created for perform Create,Read,Update,Delete requests to the user API
public class UserEndPoints2 {
	//method created for getting URL's from properties file
	static ResourceBundle getURL() {
		ResourceBundle routes=ResourceBundle.getBundle("routes");//Load properties file
		return routes;
	}
    //Create User method
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		
		Response response =given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		.when()
		  .post(post_url);
		  return response;
	}
	//get usser method
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		Response response =given()
		   .pathParam("username", userName)
		.when()
		  .get(get_url);
		  return response;
	}
	//update user method
	public static Response updateUser(User payload,String userName)
	{
		String update_url=getURL().getString("update_url");
		Response response =given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		   .pathParam("username", userName)
		.when()
		  .put(update_url);
		  return response;
	}
	//delete user method
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");
		Response response =given()
		   .pathParam("username", userName)
		.when()
		  .delete(delete_url);
		  return response;
	}
	
	
}

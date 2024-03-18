package com.GherkinKeywords;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;


public class ApiMethods {
	
	@BeforeClass
	public void setUp()
	{
		//to declare endpoint/base URI which is constant
		RestAssured.baseURI="https://reqres.in/api";
	}
	
	@Test
	public void postRequest()
	{
		//to declare the payload/to provide the payload/to initiate the payload
		String requestBody="{\r\n"
							+ "    \"name\": \"morpheus\",\r\n"
							+ "    \"job\": \"leader\"\r\n"
							+ "}";
		
		//to provide information about api and send the request and cpature the response
		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/users")
				.then()
				.extract().response();
		
		//to print respomse amd status code
		int actual_StatusCode=response.getStatusCode();
		System.out.println("Status code is: "+actual_StatusCode);
		
		int expected_StatusCode=201;
		
		String actual_ResponseBody=response.getBody().asString();
		System.out.println("Response body is: "+actual_ResponseBody);
		
		//assertions
		
		Assert.assertEquals(expected_StatusCode, actual_StatusCode);
		Assert.assertEquals("morpheus", response.jsonPath().getString("name"));
		Assert.assertEquals("leader", response.jsonPath().getString("job"));
		System.out.println("************************************************");
	}
	
	@Test
	public void putRequest()
	{
		//to declare the payload/initiate the payload
		String reqeustBody="{\r\n"
							+ "    \"name\": \"morpheus-scott\",\r\n"
							+ "    \"job\": \"zion resident\"\r\n"
							+ "}";
		
		//to provide information about api and to send the request and capture the response
		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.body(reqeustBody)
				.when()
				.put("/users/2")
				.then()
				.extract().response();
		
		//to print status code and response body
		System.out.println("Status code is: "+response.getStatusCode());
		System.out.println("Response body is: "+response.getBody().asString());
		
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("morpheus-scott", response.jsonPath().getString("name"));
		Assert.assertEquals("zion resident", response.jsonPath().getString("job"));
		System.out.println("********************************************************");
				
	}
	
	@Test
	public void getRequest()
	{
		Response response=RestAssured.given()
				.when()
				.get("/users/2")
				.then()
				.extract().response();
		
		Assert.assertEquals("Janet", response.jsonPath().getString("data.first_name"));
		Assert.assertEquals("Weaver", response.jsonPath().getString("data.last_name"));
		Assert.assertEquals("janet.weaver@reqres.in", response.jsonPath().getString("data.email"));
		
		//validating ID, check if the id is present then only validate, but if it is null do not validate
		boolean isIDPresent=response.jsonPath().get("data.id")!=null;
		Assert.assertTrue(isIDPresent);
		
		if(isIDPresent)
		{
			int expected_ID=2;
			int actual_ID=response.jsonPath().getInt("data.id");
			Assert.assertEquals(expected_ID, actual_ID);
			
		}
		
	}
	
}

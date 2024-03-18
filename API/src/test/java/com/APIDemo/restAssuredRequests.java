package com.APIDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class restAssuredRequests {
	
	@Test(priority=1,description="Creating a new resourse")
	public void createResource()
	{
		//To initiate the payload
		String requestBody="{\r\n"
							+ "    \"name\": \"morpheus\",\r\n"
							+ "    \"job\": \"leader\"\r\n"
							+ "}";
		
		//To specify payload format and payload, header, parameter format
		RequestSpecification request=RestAssured.given();
		
		request.header("Content-Type", "application/json");
		request.body(requestBody);
		
		//To send the request and to capture the response and response to be stored in a variable
		Response response=request.post("https://reqres.in/api/users");
		
		//To print response and status code
		int statusCode=response.getStatusCode();
		System.out.println("Post request status code is: "+statusCode);
		
		String responseBody=response.getBody().asString();
		System.out.println("Post request response body is: "+responseBody);
		System.out.println("***********************************************");
		
	}
	
	RequestSpecification request;
	
	@Test(priority=2,description="Updating the existing resourse")
	public void updateResource()
	{
		//To initiate the updated payload
		String requestBody="{\r\n"
							+ "    \"name\": \"morpheus\",\r\n"
							+ "    \"job\": \"Businessman\"\r\n"
							+ "}";
		
		//To specify the payload format
		request=RestAssured.given();
		
		request.header("Content-Type", "application/json");
		request.body(requestBody);
		
		//To update the existing data and capture the response
		Response response=request.put("https://reqres.in/api/users/2");
		
		//To print status code and response body
		int statusCode=response.getStatusCode();
		System.out.println("Put reqeust status code is: "+statusCode);
		
		String responseBody=response.getBody().asString();
		System.out.println("Put request response body is: "+responseBody);
		System.out.println("***********************************************");
		
	}
	
	@Test(priority=3,description="Getting the resourse")
	public void getResource()
	{
		
		//to specify the input data
		request=RestAssured.given();
		
		//to send the request and capture the response body
		Response response=request.get("https://reqres.in/api/users?page=3");
		
		//to print the status code and response body
		int getReqStatusCode=response.getStatusCode();
		System.out.println("Get request status code is: "+getReqStatusCode);
		
		String getReqResponseBody=response.getBody().asString();
		System.out.println("Get request response body is: "+getReqResponseBody);
		System.out.println("*****************************************************");
		
	}
	
	@Test(priority=4,description="Deleting the resourse")
	public void deleteResource()
	{
		request=RestAssured.given();
		
		Response response=request.delete("https://reqres.in/api/users/2");
		
		int deleteReqStatusCode=response.getStatusCode();
		System.out.println("Delete request status code is: "+deleteReqStatusCode);
		
		String deleteReqResponseBody=response.getBody().asString();
		System.out.println("Delete request response body is: "+deleteReqResponseBody);
		System.out.println("*****************************************************");
		
		
		
		
	}
	
	
	
	
}

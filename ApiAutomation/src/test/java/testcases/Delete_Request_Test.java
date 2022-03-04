package testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import constants.ParametersConst;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Delete_Request_Test extends TestBase {
	@BeforeClass
	public void getResponse() throws InterruptedException 
	{
		logger = Logger.getLogger(TestBase.class);
		System.out.println("hello yash");
		logger.info("********** Started   Get_Request_Test_1  **********");
		RestAssured.baseURI = ParametersConst.GET_REQRES_BASE_URL;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.DELETE,ParametersConst.PUT_REQRES_PATH_PARAM);
		
		Thread.sleep(3);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("********* Checking Response Body ********");
		String responseBody = response.getBody().asString();
		logger.info("Response body is ==>"+responseBody);
		Assert.assertTrue(responseBody != null);
	}
    
	@Test
	public void checkStatusCode()
	{
		logger.info("********* Checking Status Code ********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode,ParametersConst.DELETE_VALIDATE_STATUS_CODE);
	}
	
	@Test
	public void checkResponseTime()
	{
		logger.info("********* Checking Response Time ********");
		long time = response.getTime();
		logger.info("Response Time is ==>"+time);
		Assert.assertTrue(time<=ParametersConst.VALIDATE_RESPONSE_TIME);
	}
}

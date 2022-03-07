package testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import constants.ParametersConst;
import excelData.ReadDataFromExcelSheet;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Get_Request_Test extends TestBase{
	
	@DataProvider
	public Object[][] getData() throws Exception {
		Object data[][] = ReadDataFromExcelSheet.sheetData();
		return data;
	}

	@BeforeClass
	public void getResponse() throws InterruptedException 
	{
		logger = Logger.getLogger(TestBase.class);
		System.out.println("hello yash");
		logger.info("********** Started   Get_Request_Test_1  **********");
		RestAssured.baseURI = ParametersConst.GET_REQRES_BASE_URL;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,ParametersConst.GET_REQRES_PATH_PARAM);
		
//		Headers header = response.headers();
//		for(Header eachHeader:header) {
//			String contentType = eachHeader.getName().trim();
//			
//			if(contentType.equals("Content-Type")) {
//				System.out.println("Value of content type ="+eachHeader.getValue());
//			}
//			System.out.println(eachHeader.getName()+"  ---  "+eachHeader.getValue());
//		}
		
		Thread.sleep(3);
	}
	
	
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
		Assert.assertEquals(statusCode,ParametersConst.GET_VALIDATE_STATUS_CODE);
	}
	
	@Test
	public void checkResponseTime()
	{
		logger.info("********* Checking Response Time ********");
		long time = response.getTime();
		logger.info("Response Time is ==>"+time);
		Assert.assertTrue(time<=ParametersConst.VALIDATE_RESPONSE_TIME);
	}
	
	@Test
	public void checkContentType()
	{
		logger.info("********* Checking Content Type ********");
		String contentType = response.getContentType();
		logger.info("Response Time is ==>"+contentType);
		Assert.assertEquals(contentType,ParametersConst.VALIDATE_CONTENT_TYPE);
	}
	
}

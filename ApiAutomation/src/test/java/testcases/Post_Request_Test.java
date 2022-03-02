package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import constants.ParametersConst;
import excelData.ReadDataFromExcelSheet;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_Request_Test extends TestBase {
	
	@DataProvider
	public Object[][] getData() throws Exception {
		Object data[][] = ReadDataFromExcelSheet.sheetData();
		return data;
	}

	@BeforeClass()
	public void getResponse(String name, String job) {
		RestAssured.baseURI = ParametersConst.POST_REQRES_BASE;
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject responseBody = new JSONObject();
		responseBody.put(name, job);
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(responseBody.toJSONString());
		Response response = httpRequest.request(Method.POST,ParametersConst.POST_REQRES_PATH_PARAM);
	}
	
	@Test
	public void checkStatusCode() {
		logger.info("********* Checking Status Code ********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode,ParametersConst.POST_VALIDATE_STATUS_CODE);
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

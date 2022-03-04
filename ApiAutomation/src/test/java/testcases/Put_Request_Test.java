package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import constants.ParametersConst;
import excelData.ReadDataFromExcelSheet;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Put_Request_Test extends TestBase {
	
	@DataProvider
	public Object[][] getData() throws Exception {
		Object data[][] = ReadDataFromExcelSheet.sheetData();
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void testPostData(String name, String job) {
		RestAssured.baseURI = ParametersConst.REQRES_BASE;
		httpRequest = RestAssured.given();
		
		JSONObject responseBody = new JSONObject();
		responseBody.put(name, job);
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(responseBody.toJSONString());
		response = httpRequest.request(Method.PUT,ParametersConst.PUT_REQRES_PATH_PARAM);
		
		checkStatusCode();
		checkResponseBody();
		checkContentType();
		checkResponseTime();
	}

	public void checkStatusCode() {
		logger.info("********* Checking Status Code ********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode,ParametersConst.PUT_VALIDATE_STATUS_CODE);
	}

	public void checkResponseBody()
	{
		logger.info("********* Checking Response Body ********");
		String responseBody = response.getBody().asString();
		logger.info("Response body is ==>"+responseBody);
		Assert.assertTrue(responseBody != null);
	}

	public void checkResponseTime()
	{
		logger.info("********* Checking Response Time ********");
		long time = response.getTime();
		logger.info("Response Time is ==>"+time);
		Assert.assertTrue(time<=ParametersConst.VALIDATE_RESPONSE_TIME);
	}

	public void checkContentType()
	{
		logger.info("********* Checking Content Type ********");
		String contentType = response.getContentType();
		logger.info("Response Time is ==>"+contentType);
		Assert.assertEquals(contentType,ParametersConst.VALIDATE_CONTENT_TYPE);
	}
}

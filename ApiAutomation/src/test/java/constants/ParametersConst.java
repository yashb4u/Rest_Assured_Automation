package constants;

public class ParametersConst {

	public static final String REQRES_BASE                         = "https://reqres.in";
	public static final String POST_REQRES_PATH_PARAM              = "/api/users";
	
	public static final String GET_REQRES_BASE_URL                 = "https://reqres.in/api";
	public static final String GET_REQRES_PATH_PARAM               = "/users?page=2";
	public static final String PUT_REQRES_PATH_PARAM               = "/api/users/2";
	
	
	//-------Validation Constants----
	
	public static final String VALIDATE_CONTENT_TYPE               = "application/json; charset=utf-8";
	public static final long VALIDATE_RESPONSE_TIME                = 5000;
	public static final int POST_VALIDATE_STATUS_CODE              = 201;
	public static final int GET_VALIDATE_STATUS_CODE               = 200;
	public static final int PUT_VALIDATE_STATUS_CODE               = 200;
	public static final int DELETE_VALIDATE_STATUS_CODE            = 204;
	
}

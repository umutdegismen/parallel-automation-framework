package com.parallel.api;

public class NeotechSchoolEndoints {
	
	// NEOTECH school app endpoints
	// token endpoint
	public static final String GENERATE_TOKEN_ENDPOINT = "/api/TokenAuth/Authenticate";

	// classes endpoints
	public static final String GET_ALL_CLASSES_ENDPOINT = "/api/services/app/Class/GetAll";
	public static final String GET_ONE_CLASS_ENDPOINT = "/api/services/app/Class/Get/{Id}";

	// students endpoints
	public static final String GET_ALL_STUDENTS_ENDPOINT = "/api/services/app/Student/GetAll";
	public static final String CREATE_STUDENT_ENDPOINT = "/api/services/app/Student/Create";
	public static final String GET_ONE_STUDENT = "/api/services/app/Student/Get";
}

package com.platform.registration.constants;

import org.springframework.stereotype.Component;

@Component
public class RegistrationConstants {
	private RegistrationConstants() {

	}

	public static final String REGISTRATION_OPERATION_VALUE = "Registration Service";
	public static final String REGISTRATION_OPERATION_NOTES = "This Api is used to register a new users.";
	public static final String REGISTRATION_OPERATION_TAGS = "Registration";

	public static final String SIGN_IN_OPERATION_VALUE = "Sign in Service";
	public static final String SIGN_IN_OPERATION_NOTES = "This Api is used to sign in users.";

	public static final String USER_OPERATION_VALUE = "User Service";
	public static final String USER_OPERATION_NOTES = "This Api is used to fetch user details.";
	public static final String USER_OPERATION_TAGS = "User";

	public static final String VALIDATION_ERROR = "Validation Error";
	public static final String PROCESSING_ERROR = "Processing Error";
	public static final String VALIDATION_ERROR_DETAILS = "Missing one or more fields";
	public static final String PROCESSING_ERROR_DETAILS = "The service encountered an error. Please try again after sometime.";

}
package com.taskmanager.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

	public static final String ERROR_INVALID_USERNAME = "Invalid username : ";
	public static final String ERROR_USER_NOT_FOUND_USERNAME = "User Doesn't Exists with username : ";
	public static final String ERROR_INVALID_USERNAME_OR_PASSWORD = "Invalid UserName or Email";
	public static final String ERROR_WHILE_FINDING_USER = "Error Occurred while Finding User Details";
	public static final String ROLE_UNDEFINED = "Unable to determine role";
	public static final String ACCESS_DENIED = "Access Denied: User does not have the required role";
	public static final String SYSTEM_ACCESS_TOKEN_EXPIRED = "System Access Token Expired";
	public static final String REFRESH_TOKEN_EXPIRED = "Refresh Token Expired";
	public static final String INVALID_TOKEN = "Token missing or invalid format.";
	public static final String ACCESS_TOKEN_EXPIRED = "Access token expired for user: %s";
	public static final String INACTIVE_USER = "Inactive Account or Account Blocked";
	public static final String INCORRECT_PASSWORD = "Incorrect Password";
	public static final String ERROR_INVALID_REFRESH_TOKEN = "Invalid Refresh Token";
	public static final String ERROR_INVALID_ID = "Invalid username : ";
	public static final String ERROR_USER_NOT_FOUND_ID = "User Doesn't Exists with id : ";
	public static final String INVALID_USER_ID = "Invalid User Id";
}

package com.taskmanager.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {

    public static final String BASE_PACKAGE = "com.taskmanager";

    public static final String AUTH_SVC = "AUTH-SVC";
    public static final String USER_SVC = "USER-SVC";
    public static final String NOTIFY_SVC = "NOTIFY-SVC";
    public static final String FILE_SVC = "FILE-SVC";
    public static final String TASK_SVC = "TASK-SVC";
    public static final String GATEWAY_SVC = "GATEWAY-SVC";
    public static final String REPORT_SVC = "REPORT-SVC";

    public static final String FORWARD_SLASH = "/";

    public static final String V1 = "v1";
    public static final String API_PATH = "api";
    public static final String USERS = "users";
    public static final String TASKS = "tasks";
    public static final String AUTH = "auth";
    public static final String TASK = "task";
    public static final String COMMENTS = "comments";

    public static final String PATH_REGISTER = "register";
    public static final String PATH_USER = "user";
    public static final String PATH_USERNAME = "username";
    public static final String PATH_LOGIN = "login";
    public static final String PATH_LOGOUT = "logout";
    public static final String PATH_SEARCH = "search";
    public static final String PATH_REFRESH_TOKEN = "refresh-token";
    public static final String PATH_ROLE_STATUS = "role-status";
    public static final String PATH_ASSIGNEE = "assignee";
    public static final String PATH_STATUS = "status";

    public static final String PATH_VARIABLE_ID = "/{id}";
    public static final String PATH_VARIABLE_USERNAME = "/{username}";
    public static final String PATH_VARIABLE_TASK_ID = "/{taskId}";
    public static final String PATH_VARIABLE_USER_ID = "/{userId}";

    public static final String BASE_URL = API_PATH + FORWARD_SLASH + V1;
    public static final String BASE_URL_USER_V1 = BASE_URL + FORWARD_SLASH + USERS;
    public static final String BASE_URL_AUTH_V1 = BASE_URL + FORWARD_SLASH + AUTH;
    public static final String BASE_URL_TASK_V1 = BASE_URL + FORWARD_SLASH + TASKS;
    public static final String BASE_URL_COMMENT_V1 = BASE_URL + FORWARD_SLASH + COMMENTS;

    public static final String SPACE = " ";
    public static final String BEARER = "Bearer";

    public static final String API_AUTH_LOGIN = FORWARD_SLASH + PATH_LOGIN;
    public static final String API_AUTH_REFRESH_TOKEN = FORWARD_SLASH + PATH_REFRESH_TOKEN;
    public static final String API_AUTH_LOGOUT = FORWARD_SLASH + PATH_LOGOUT;

    public static final String API_USERS_REGISTER = FORWARD_SLASH + PATH_REGISTER;
    public static final String API_GET_USER_BY_USERNAME = FORWARD_SLASH + PATH_USERNAME + PATH_VARIABLE_USERNAME;
    public static final String API_GET_USER_BY_ID = PATH_VARIABLE_ID;
    public static final String API_UPDATE_USER = PATH_VARIABLE_ID;
    public static final String API_UPDATE_STATUS_ROLE = FORWARD_SLASH + PATH_ROLE_STATUS + PATH_VARIABLE_ID;

    public static final String API_SEARCH = FORWARD_SLASH + PATH_SEARCH;
    public static final String TASK_ID_PATH = PATH_VARIABLE_TASK_ID;
    public static final String ASSIGNED_TO_USER_API =  PATH_VARIABLE_TASK_ID + FORWARD_SLASH + PATH_ASSIGNEE + PATH_VARIABLE_USER_ID ;

    public static final String UPDATE_STATUS_API = PATH_VARIABLE_TASK_ID + FORWARD_SLASH + PATH_STATUS;
    public static final String REQUEST_PARAM_IS_DETAILS_REQUIRED ="isDetailsRequired" ;
    public static final String REQUEST_PARAM_INCLUDE_INACTIVE ="includeInactive" ;
}

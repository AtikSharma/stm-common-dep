package com.taskmanager.common.client;

import com.taskmanager.common.model.User;

public interface UserServiceClient {

	public User getUserDetailsByUsername(String username);

	public User getUserDetailsById(String userId);
}

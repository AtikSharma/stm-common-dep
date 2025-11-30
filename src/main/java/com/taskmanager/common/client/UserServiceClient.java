package com.taskmanager.common.client;

import com.taskmanager.common.model.User;

import java.util.List;

public interface UserServiceClient {

    public User getUserDetailsByUsername(String username, boolean fetchSensitiveInfo);

    public User getUserDetailsById(String userId, boolean fetchSensitiveInfo);

    public List<User> getAllUsers(boolean fetchSensitiveInfo);

}

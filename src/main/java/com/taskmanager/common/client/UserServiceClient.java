package com.taskmanager.common.client;

import com.taskmanager.common.model.User;

import java.util.List;

public interface UserServiceClient {

    public User getUserDetailsByUsername(String username);

    public User getUserDetailsById(String userId);

    public List<User> getAllUsers();
}

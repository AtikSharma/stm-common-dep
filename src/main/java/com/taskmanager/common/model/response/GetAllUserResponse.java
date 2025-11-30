package com.taskmanager.common.model.response;

import com.taskmanager.common.model.User;

import java.util.List;

public record GetAllUserResponse(List<User> users) {
}

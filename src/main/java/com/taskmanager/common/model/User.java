package com.taskmanager.common.model;

import com.taskmanager.common.enums.Role;
import com.taskmanager.common.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class User extends UserBase {

    private String username;

    private String email;

    private String password;

    private Status status;

    private Role role;
}

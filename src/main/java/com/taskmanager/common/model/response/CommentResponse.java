package com.taskmanager.common.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CommentResponse {

    private String commentId;

    private String content;

    private UserResponse commentedBy;

    private String createdAt;

    private String updatedAt;

}

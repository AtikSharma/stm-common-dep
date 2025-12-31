package com.taskmanager.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CommentBase extends Audit{

    private String id;

    private String commentedBy;

    private String content;

    private String taskId;

    private boolean isDeleted;

    private LocalDateTime deletedAt;

    private String deletedBy;
}

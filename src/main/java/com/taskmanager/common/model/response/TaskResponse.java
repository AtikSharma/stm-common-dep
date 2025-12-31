package com.taskmanager.common.model.response;

import com.taskmanager.common.enums.Priority;
import com.taskmanager.common.enums.TaskStatus;
import com.taskmanager.common.model.UserBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class TaskResponse {

    private String taskId;

    private Long taskNumber;

    private String title;

    private String description;

    private TaskStatus status;

    private Priority priority;

    private LocalDate dueDate;

    private UserResponse assignedTo;

    private UserResponse createdBy;

    private UserResponse updatedBy;

    private List<CommentResponse> comments;

    private LocalDateTime creationTime;

    private LocalDateTime updateTime;
}

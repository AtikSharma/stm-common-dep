package com.taskmanager.common.model;

import com.taskmanager.common.enums.Priority;
import com.taskmanager.common.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class TaskBase {
    private String id;

    private Long taskNumber;

    private String title;

    private String description;

    private TaskStatus status;

    private Priority priority;

    private UserBase createdBy;

    private UserBase assignedTo;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

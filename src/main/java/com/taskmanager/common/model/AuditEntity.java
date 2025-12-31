package com.taskmanager.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AuditEntity {

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String updatedBy;
}

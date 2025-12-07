package com.taskmanager.common.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class SearchRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private Sort.Direction sortDirection;
}
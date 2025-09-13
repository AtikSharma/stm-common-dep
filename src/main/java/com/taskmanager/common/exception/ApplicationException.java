package com.taskmanager.common.exception;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> params;

    @Getter
    private HttpStatus status;

    public ApplicationException(Exception e) {
        super(e);
    }

    public ApplicationException(String message, String... params) {
        super(message);
        if (params != null) {
            this.params = new ArrayList<>();
            Collections.addAll(this.params, params);
        }
    }

    public ApplicationException(String message, Exception e) {
        super(message, e);
    }

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public String getParamsAsString() {
        if (params != null) {
            StringBuilder sb = new StringBuilder();
            params.forEach(param -> {
                sb.append(param);
                sb.append(",");
            });
            return sb.toString();
        }
        return "";
    }
}

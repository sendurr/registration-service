package com.platform.registration.error;

import lombok.Data;

@Data
public class ServiceValidationError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ServiceValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ServiceValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}


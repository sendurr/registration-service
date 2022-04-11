package com.platform.registration.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class ServiceException extends RuntimeException {
    private String errorCode;
    private String shortMessage;
    private String detailedMessage;
    private HttpStatus status;
    private transient List<ServiceValidationError> validationErrors = null;
    transient Object[] args = null;

    public ServiceException() {
    }

    public ServiceException(String errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, HttpStatus status, Object[] args) {
        this.errorCode = errorCode;
        this.status = status;
        this.args = args;
    }

    public ServiceException(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public ServiceException(HttpStatus status, String shortMessage, String detailedMessage) {
        this.detailedMessage = detailedMessage;
        this.shortMessage = shortMessage;
        this.status = status;
    }

    public ServiceException(String errorCode, HttpStatus status, List<ServiceValidationError> validationErrors) {
        this.errorCode = errorCode;
        this.status = status;
        this.validationErrors = validationErrors;
    }

    public ServiceException(HttpStatus status, String shortMessage, String detailedMessage, List<ServiceValidationError> validationErrors) {
        this.detailedMessage = detailedMessage;
        this.shortMessage = shortMessage;
        this.status = status;
        this.validationErrors = validationErrors;
    }
}

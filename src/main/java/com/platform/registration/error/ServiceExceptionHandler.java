package com.platform.registration.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.sasl.AuthenticationException;
import java.text.MessageFormat;

@Order(-2147483648)
@ControllerAdvice
@Component
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    public ServiceExceptionHandler() {
    }

    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(ServiceException sEx) {
        ServiceError serviceError = new ServiceError();
        serviceError.setMessage(sEx.getShortMessage());
        serviceError.setDetailMessage(sEx.getDetailedMessage());
//        serviceError.setErrorCode(sEx.getErrorCode());
        serviceError.setErrorCode("SERVICE_ERR_01");
        serviceError.setValidationErrors(sEx.getValidationErrors());

        return new ResponseEntity(serviceError, sEx.getStatus());
    }
}

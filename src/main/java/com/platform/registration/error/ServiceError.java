package com.platform.registration.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Order(-2147483648)
@JsonTypeIdResolver(TypeIdResolverBase.class)
@Component
public class ServiceError {
    private static final long serialVersionUID = 1L;
    private String message;
    private String detailMessage;
    private String debugMessage;
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ServiceValidationError> validationErrors;

    public ServiceError() {
    }
}

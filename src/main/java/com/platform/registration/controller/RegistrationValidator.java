package com.platform.registration.controller;

import com.platform.registration.constants.RegistrationConstants;
import com.platform.registration.dto.UserSignInRequest;
import com.platform.registration.dto.UserSignUpRequest;
import com.platform.registration.error.ServiceException;
import com.platform.registration.error.ServiceValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationValidator {

    public void validateSignUpRequest(UserSignUpRequest request) {
        boolean hasRequest = request != null;
        List<ServiceValidationError> errors = new ArrayList<>();

        if (!hasRequest) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, RegistrationConstants.VALIDATION_ERROR, "Empty request", errors);
        }

        if(StringUtils.isEmpty(request.getEmailId())){
            errors.add(new ServiceValidationError("emailId", "Email id is required."));
        }

        if(StringUtils.isEmpty(request.getUserName())){
            errors.add(new ServiceValidationError("userName", "User name is required."));
        }

        if(StringUtils.isEmpty(request.getFirstName())){
            errors.add(new ServiceValidationError("firstName", "First name is required."));
        }

        if(StringUtils.isEmpty(request.getLastName())){
            errors.add(new ServiceValidationError("lastName", "Last name is required."));
        }

        if(StringUtils.isEmpty(request.getPassword())){
            errors.add(new ServiceValidationError("password", "Password is required."));
        }

        if(StringUtils.isEmpty(request.getPhoneNumber())){
            errors.add(new ServiceValidationError("phoneNumber", "Phone is required."));
        }

        if (!errors.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, RegistrationConstants.VALIDATION_ERROR, RegistrationConstants.VALIDATION_ERROR_DETAILS, errors);
        }

    }

    public void validateSignInRequest(UserSignInRequest request) {
        boolean hasRequest = request != null;
        List<ServiceValidationError> errors = new ArrayList<>();

        if (!hasRequest) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, RegistrationConstants.VALIDATION_ERROR, "Empty request", errors);
        }

        if(StringUtils.isEmpty(request.getUserName())){
            errors.add(new ServiceValidationError("userName", "User name is required."));
        }

        if(StringUtils.isEmpty(request.getPassword())){
            errors.add(new ServiceValidationError("password", "Password is required."));
        }

        if (!errors.isEmpty()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, RegistrationConstants.VALIDATION_ERROR, RegistrationConstants.VALIDATION_ERROR_DETAILS, errors);
        }

    }
}

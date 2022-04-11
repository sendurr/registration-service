package com.platform.registration.controller;

import com.platform.registration.constants.RegistrationConstants;
import com.platform.registration.dto.SignInResponse;
import com.platform.registration.dto.SignUpResponse;
import com.platform.registration.dto.UserSignInRequest;
import com.platform.registration.dto.UserSignUpRequest;
import com.platform.registration.error.ServiceException;
import com.platform.registration.service.RegistrationService;
import com.platform.registration.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RegistrationValidator registrationValidator;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = RegistrationConstants.REGISTRATION_OPERATION_VALUE, notes = RegistrationConstants.REGISTRATION_OPERATION_NOTES, tags = { RegistrationConstants.REGISTRATION_OPERATION_TAGS })
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully registered new user.", response = SignUpResponse.class),
            @ApiResponse(code = 400, message = "Bad request.", response = ServiceException.class),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ServiceException.class),
            @ApiResponse(code = 500, message = "Internal error.", response = ServiceException.class)})
    @PostMapping(value = "/auth/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> registerUser(@RequestBody UserSignUpRequest userSignUpRequest) {

        log.info("Processing user signup.");

        ResponseEntity<Object> responseEntity = null;

        // Validate user request
        registrationValidator.validateSignUpRequest(userSignUpRequest);

        registrationService.signUpUser(userSignUpRequest);

        responseEntity = new ResponseEntity<>(new SignUpResponse("Successful registered user."), HttpStatus.OK);
        log.info("Successful registered user.");

        return responseEntity;
    }

    @ApiOperation(value = RegistrationConstants.SIGN_IN_OPERATION_VALUE, notes = RegistrationConstants.SIGN_IN_OPERATION_NOTES, tags = { RegistrationConstants.REGISTRATION_OPERATION_TAGS })
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully signed in user.", response = SignUpResponse.class),
            @ApiResponse(code = 400, message = "Bad request.", response = ServiceException.class),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ServiceException.class),
            @ApiResponse(code = 404, message = "User not found", response = ServiceException.class),
            @ApiResponse(code = 500, message = "Internal error.", response = ServiceException.class)})
    @PostMapping(value = "/auth/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> signInUser(@RequestBody UserSignInRequest userSignInRequest) {

        log.info("Processing user sign in.");

        ResponseEntity<Object> responseEntity = null;

        // Validate user request
        registrationValidator.validateSignInRequest(userSignInRequest);

        var user = registrationService.signInUser(userSignInRequest);

        responseEntity = new ResponseEntity<>(new SignInResponse("Successful signed in user.",
                jwtTokenUtil.generateToken(user),
                user.getId()), HttpStatus.OK);
        log.info("Successful signed in user.");

        return responseEntity;
    }

}
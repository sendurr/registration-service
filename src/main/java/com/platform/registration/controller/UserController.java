package com.platform.registration.controller;

import com.platform.registration.constants.RegistrationConstants;
import com.platform.registration.dto.*;
import com.platform.registration.error.ServiceException;
import com.platform.registration.model.User;
import com.platform.registration.service.RegistrationService;
import com.platform.registration.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    RegistrationValidator registrationValidator;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = RegistrationConstants.USER_OPERATION_VALUE, notes = RegistrationConstants.USER_OPERATION_NOTES, tags = { RegistrationConstants.USER_OPERATION_TAGS })
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully fetched user.", response = User.class),
            @ApiResponse(code = 404, message = "User not found", response = ServiceException.class),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ServiceException.class),
            @ApiResponse(code = 500, message = "Internal error.", response = ServiceException.class)})
    @GetMapping(value = "/user/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getUserDetails(@PathVariable(value = "user-id") String userId) {

        log.info("Processing user fetch.");

        ResponseEntity<Object> responseEntity = null;

        var user = registrationService.getUserDetails(userId);

        responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        log.info("Successful fetched user.");

        return responseEntity;
    }

    @ApiOperation(value = RegistrationConstants.USER_OPERATION_VALUE, notes = RegistrationConstants.USER_OPERATION_NOTES, tags = { RegistrationConstants.USER_OPERATION_TAGS })
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully searched for user using email id .", response = UserList.class),
            @ApiResponse(code = 404, message = "Not users found", response = ServiceException.class),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ServiceException.class),
            @ApiResponse(code = 500, message = "Internal error.", response = ServiceException.class)})
    @GetMapping(value = "/user/_search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> searchUserByEmailId(@RequestParam String emailId) {

        log.info("Processing search users by email id.");

        ResponseEntity<Object> responseEntity = null;

        var userList = registrationService.getUserByEmailId(emailId);

        responseEntity = new ResponseEntity<>(userList, HttpStatus.OK);
        log.info("Successful completed search users by email id..");

        return responseEntity;
    }

}
package com.platform.registration.service.imp;


import com.platform.registration.constants.RegistrationConstants;
import com.platform.registration.dto.UserList;
import com.platform.registration.dto.UserSignInRequest;
import com.platform.registration.dto.UserSignUpRequest;
import com.platform.registration.error.ServiceException;
import com.platform.registration.model.User;
import com.platform.registration.repository.UserRepository;
import com.platform.registration.service.RegistrationService;
import com.platform.registration.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    public void signUpUser(UserSignUpRequest request){

        log.info("Executing signUpUser service.");
        User user = null;

        try {
            // Find if user is already available
            user = userRepository.findByUsernameOrEmailid(request.getUserName(), request.getEmailId());
        } catch (Exception e) {
            log.error("Error in signUpUser service.", e.getMessage());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS);

        }

        // User existing - throw an error
        if(!ObjectUtils.isEmpty(user)){
            throw new ServiceException(HttpStatus.BAD_REQUEST, "User already available.", "Please sign up with a different email id and user name.");
        }

        try {
            // User not available - create new user
            var newUser = new User(request.getUserName(), request.getEmailId(), request.getPassword(),
                    request.getFirstName(), request.getLastName(), request.getPhoneNumber());
            userRepository.save(newUser);
            log.info("Completed signUpUser service.");
        } catch (Exception e) {
            log.error("Error in signUpUser service.", e.getMessage());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS);
        }

    }

    public User signInUser(UserSignInRequest request){

        log.info("Executing signInUser service.");
        User user = null;

        try {
            // Find if user by user name and password.
            user = userRepository.findByUsernameAndPassword(request.getUserName(), request.getPassword());
        } catch (Exception e) {
            log.error("Error in signUpUser service.", e.getMessage());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS);

        }

        // User not found - throw an error
        if(ObjectUtils.isEmpty(user)){
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Incorrect user name or password.", "Please sign in with correct user name and password.");
        }

        log.info("Completed signInUser service.");

        return user;

    }

    public User getUserDetails(String userId){

        log.info("Executing getUserDetails service.");
        Optional<User> user = null;

        try {
            // Find user by id.
            user = userRepository.findById(userId);
        } catch (Exception e) {
            log.error("Error in getUserDetails service.", e.getMessage());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS);

        }

        // User not found - throw an error
        if(ObjectUtils.isEmpty(user)){
            throw new ServiceException(HttpStatus.NOT_FOUND, "User not found.", "Please check user id.");
        }

        log.info("Completed getUserDetails service.");

        return user.get();

    }

    public UserList getUserByEmailId(String emailId){

        log.info("Executing getUserByEmailId service.");
        List<User> users = null;
        UserList userList = new UserList();

        try {
            // Find user by emailId.
            users = userRepository.findByEmailid(emailId);
        } catch (Exception e) {
            log.error("Error in getUserByEmailId service.", e.getMessage());
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS);

        }

        // User not found - throw an error
        if(ObjectUtils.isEmpty(users)){
            throw new ServiceException(HttpStatus.NOT_FOUND, "No users found.", "No users found for given email id.");
        }

        userList.setUserList(users);
        log.info("Completed getUserDetails service.");

        return userList;

    }
}

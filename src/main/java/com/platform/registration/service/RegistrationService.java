package com.platform.registration.service;


import com.platform.registration.dto.UserList;
import com.platform.registration.dto.UserSignInRequest;
import com.platform.registration.dto.UserSignUpRequest;
import com.platform.registration.model.User;

import java.util.List;

public interface RegistrationService {

    void signUpUser(UserSignUpRequest request);

    User signInUser(UserSignInRequest request);

    User getUserDetails(String userId);

    UserList getUserByEmailId(String emailId);

}

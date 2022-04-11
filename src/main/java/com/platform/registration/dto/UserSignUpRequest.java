package com.platform.registration.dto;

import lombok.Data;

@Data
public class UserSignUpRequest {

    private String userName;
    private String emailId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}

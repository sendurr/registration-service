package com.platform.registration.dto;

import lombok.Data;

@Data
public class UserSignInRequest {

    private String userName;
    private String password;
}

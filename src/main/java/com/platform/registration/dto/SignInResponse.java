package com.platform.registration.dto;

import lombok.Data;

@Data
public class SignInResponse {

    private String message;
    private String token;
    private String userId;

    public SignInResponse(String msg, String token, String userId){
        setMessage(msg);
        setToken(token);
        setUserId(userId);
    }
}

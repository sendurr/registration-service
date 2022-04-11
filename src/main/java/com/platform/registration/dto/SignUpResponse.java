package com.platform.registration.dto;

import lombok.Data;

@Data
public class SignUpResponse {

    private String message;

    public SignUpResponse(String msg){
        setMessage(msg);
    }
}

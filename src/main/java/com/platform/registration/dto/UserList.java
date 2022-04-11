package com.platform.registration.dto;

import com.platform.registration.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserList {
    private List<User> userList;
}

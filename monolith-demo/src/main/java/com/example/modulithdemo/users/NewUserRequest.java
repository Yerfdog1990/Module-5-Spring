package com.example.modulithdemo.users;

import lombok.Data;

@Data
public class NewUserRequest {
    private String email;
    private String password;
}

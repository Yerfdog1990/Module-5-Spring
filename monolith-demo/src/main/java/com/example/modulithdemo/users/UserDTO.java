package com.example.modulithdemo.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private final Integer id;
    private final String email;
    private  final String password;

    public UserDTO(User userEntity) {
        this(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword());

    }
}

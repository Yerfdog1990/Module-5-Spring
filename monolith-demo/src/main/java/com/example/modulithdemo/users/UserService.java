package com.example.modulithdemo.users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public int createUser(String email, String password){
        User user = new User(email, password);
        return userRepository.save(user).getId();
    }
    @Transactional(readOnly = true)
    public Collection<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }
}

package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAll();

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long userId);

}

package com.example.service;

import com.example.dto.UserDto;
import com.example.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    List<UserDto> getAll();

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);

}

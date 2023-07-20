package com.example.serviceimpl;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto user) {
        User newUSer = this.userRepository.save(UserMapper.mapToUser(user));
        return UserMapper.mapToUserDto(newUSer);
    }


    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        this.userRepository.findAll().forEach(t -> users.add(UserMapper.mapToUserDto(t)));
        return users;
    }

    @Override
    public UserDto getUserById(Long id) {
        User foundUser = this.userRepository.findById(id).orElse(null);
        return UserMapper.mapToUserDto(foundUser);
    }

    @Override
    public UserDto updateUser(UserDto user) {

        User oldUser = this.userRepository.findById(user.getId()).orElse(null);
        oldUser.setId(oldUser.getId());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        User newUSer = this.userRepository.save(oldUser);
        return UserMapper.mapToUserDto(newUSer);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.delete(this.userRepository.findById(userId).get());
    }


}

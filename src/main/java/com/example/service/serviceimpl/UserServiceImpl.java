package com.example.service.serviceimpl;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto user) {
        User newUSer = this.userRepository.save(this.getUser(user));
        return this.getUserDto(newUSer);
    }


    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        this.userRepository.findAll().forEach(t -> users.add(this.getUserDto(t)));
        return users;
    }


    @Override
    public UserDto getUserById(Long id) {
        User foundUser = this.userRepository.findById(id).orElse(null);
        return this.getUserDto(foundUser);
    }

    @Override
    public UserDto updateUser(UserDto user) {

        User oldUser = this.userRepository.findById(user.getId()).orElse(null);
        oldUser.setId(oldUser.getId());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        User newUSer = this.userRepository.save(oldUser);
        return this.getUserDto(newUSer);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.delete(this.userRepository.findById(userId).get());
    }

    private UserDto getUserDto(User t) {
        return modelMapper.map(t, UserDto.class);
    }

    private User getUser(UserDto t) {
        return modelMapper.map(t, User.class);
    }
}

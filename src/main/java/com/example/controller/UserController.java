package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.FOUND);
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> getStudents(@RequestBody UserDto user) {
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id , @RequestBody UserDto user){
        return  new ResponseEntity<>(this.userService.updateUser(user) , HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted Successfully " , HttpStatus.OK);
    }
}
package com.example.rebuilding_blogapi.controller;

import com.example.rebuilding_blogapi.dto.UserDto;
import com.example.rebuilding_blogapi.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userdto) {

        return userService.createUser(userdto);
    }

    @PutMapping("/{id}")
    public UserDto putMethodName(@PathVariable Integer id, @RequestBody UserDto userdto) {

        return userService.updateUser(userdto, id);
    }

    @GetMapping("/{id}")
    public UserDto getMethodName(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<UserDto> getMethodName() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteusermethod(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}

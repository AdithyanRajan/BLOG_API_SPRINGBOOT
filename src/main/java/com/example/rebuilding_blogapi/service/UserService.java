package com.example.rebuilding_blogapi.service;


import java.util.List;
import com.example.rebuilding_blogapi.dto.UserDto;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userid);
    UserDto getUserById(Integer userid);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userid);

}

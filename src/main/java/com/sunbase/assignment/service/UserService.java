package com.sunbase.assignment.service;

import com.sunbase.assignment.dto.UserDto;
import com.sunbase.assignment.entity.User;

import java.util.List;

public interface UserService {
    static void saveOrUpdate(User user) {
    }

    void saveUser(UserDto userDto);

    List<UserDto> findAllUsers();
    User updateUser(User user,
                                Long userId);

    //


    User findUserByEmail(String email);

    void deleteUser(long id);
}
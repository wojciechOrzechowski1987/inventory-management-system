package com.worzech.inventorymanagementsystem.service.user;

import com.worzech.inventorymanagementsystem.domain.User;
import com.worzech.inventorymanagementsystem.model.User.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    User findUserByUsername(String username);

    User createNewUser(String firstName, String lastName, String username, String password, String email);

}

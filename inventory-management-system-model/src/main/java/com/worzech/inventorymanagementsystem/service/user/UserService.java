package com.worzech.inventorymanagementsystem.service.user;

import com.worzech.inventorymanagementsystem.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User findUserByUsername(String username);

    User createNewUser(String firstName, String lastName, String username, String password, String email);

}

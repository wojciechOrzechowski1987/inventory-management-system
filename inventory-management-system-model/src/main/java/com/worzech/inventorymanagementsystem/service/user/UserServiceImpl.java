package com.worzech.inventorymanagementsystem.service.user;

import com.worzech.inventorymanagementsystem.domain.User;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.user.UserDtoMapper;
import com.worzech.inventorymanagementsystem.model.User.UserDto;
import com.worzech.inventorymanagementsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public List<UserDto> getAllUsers() {
            return userRepository
                    .findAll()
                    .stream()
                    .map(userDtoMapper::toUserDto)
                    .collect(Collectors.toList());
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User createNewUser(String firstName, String lastName, String username, String password, String email) {
        return null;
    }
}

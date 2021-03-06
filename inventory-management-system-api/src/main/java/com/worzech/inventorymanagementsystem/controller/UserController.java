package com.worzech.inventorymanagementsystem.controller;


import com.worzech.inventorymanagementsystem.domain.User;
import com.worzech.inventorymanagementsystem.domain.security.SecurityUser;
import com.worzech.inventorymanagementsystem.model.User.UserDto;
import com.worzech.inventorymanagementsystem.service.user.UserService;
import com.worzech.inventorymanagementsystem.utility.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"/user"})
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;

    @CrossOrigin(exposedHeaders = "authorization")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        SecurityUser securityUser = new SecurityUser(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(securityUser);
        return new ResponseEntity<>(securityUser.getAuthorities().toString(), jwtHeader, OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(SecurityUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwtTokenProvider.generateJwtToken(user));
        headers.add("Username", user.getUsername());
        return headers;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }


}

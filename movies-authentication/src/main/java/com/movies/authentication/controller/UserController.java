package com.movies.authentication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movies.authentication.dto.SignInDTO;
import com.movies.authentication.dto.SignUpRequest;
import com.movies.authentication.entity.User;
import com.movies.authentication.response.JwtAuthenticationResponse;
import com.movies.authentication.service.UserService;

@RestController
@RequestMapping(value = "/user") 
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> createUser(@Valid @RequestBody SignUpRequest userRequest) {
        User newUser = userService.createUser(userRequest);
        return ResponseEntity.ok(newUser);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> signInUser(@Valid @RequestBody SignInDTO signInDTO) {
        JwtAuthenticationResponse response = userService.signInUser(signInDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User deleteUser = userService.removeUser(id);
        return ResponseEntity.ok(deleteUser);
    }
    
}

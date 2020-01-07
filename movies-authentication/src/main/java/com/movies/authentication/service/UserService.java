package com.movies.authentication.service;

import java.util.Collections;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movies.authentication.dto.SignInDTO;
import com.movies.authentication.dto.SignUpRequest;
import com.movies.authentication.entity.Role;
import com.movies.authentication.entity.User;
import com.movies.authentication.exception.UserAlreadyExistsException;
import com.movies.authentication.repository.UserRepository;
import com.movies.authentication.response.JwtAuthenticationResponse;
import com.movies.authentication.security.JwtTokenProvider;
import com.movies.authentication.util.password.PasswordUtil;

@Service
public class UserService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = PasswordUtil.getPasswordEncoder();
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User createUser(SignUpRequest userRequest) {
    	logger.info("creatingUser: "+ userRequest.getUsername());
        if (userRepository.findUserByUsername(userRequest.getUsername()) == null) {
            User newUser = new User();

            setUser(userRequest, newUser);

            setAndUpdateRoles(newUser);

            return createUser(newUser);
        } else {
            throw new UserAlreadyExistsException("User already registered!");
        }
    }

    public JwtAuthenticationResponse signInUser(SignInDTO userRequest) {
    	
    	logger.info("signInUser: "+ userRequest.getUsername());
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUsername(),
                        userRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }

    public User removeUser(Long id) {    	
    	
        User userToRemove = userRepository.getOne(id);
        if (userToRemove != null) {
        	logger.info("removeUser: "+ userToRemove.getUsername());
            userRepository.delete(userToRemove);
            return userToRemove;
        } else {
            throw new EntityNotFoundException("There is no user with this id: "+id);
        }
    }

    private void setAndUpdateRoles(User newUser) {
        Role userRole = roleService.getRoleByName("ROLE_USER");
        newUser.setRoles(Collections.singletonList(userRole));

        userRole.getUsers().add(newUser);
        roleService.updateRole(userRole);
    }

    private void setUser(SignUpRequest userRequest, User newUser) {
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setUsername(userRequest.getUsername());
        String password = userRequest.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);
    }

    private User createUser(User user) {
        return userRepository.save(user);
    }
}

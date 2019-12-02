package com.example.retailapplication.controller;

import com.example.retailapplication.dto.UserDTO;
import com.example.retailapplication.exception.RetailApplicationException;
import com.example.retailapplication.request.UserRequest;
import com.example.retailapplication.exception.UserConflictException;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.service.UserService;
import com.example.retailapplication.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is used to receive user related requests
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * To login into the application
     * @param userRequest to store request
     * @return response that store data in the form of user DTO
     * @throws RetailApplicationException with the following detail:
         * @throws DataNotFoundException when user does not exist in database
         * @throws UserConflictException when user already logged in before
     */
    @PostMapping("/login")
    public Response<UserDTO> login(@RequestBody @Valid UserRequest userRequest) throws RetailApplicationException {
        return userService.login(userRequest.getUsername(), userRequest.getPassword());
    }

    /**
     * To register new user
     * @param userRequest to store request
     * @return response that store data in the form of user DTO
     * @throws UserConflictException when user has been registered
     */
    @PostMapping("/register")
    public Response<UserDTO> register(@RequestBody @Valid UserRequest userRequest) throws UserConflictException {
        return userService.register(userRequest.getUsername(), userRequest.getPassword());
    }

    /**
     * To get all user data
     * @return response that store data in the form of user DTO list
     * @throws DataNotFoundException when data is empty
     */
    @PostMapping("/userlist")
    public Response<List<UserDTO>> getUserList() throws DataNotFoundException {
        return userService.getUserList();
    }
}

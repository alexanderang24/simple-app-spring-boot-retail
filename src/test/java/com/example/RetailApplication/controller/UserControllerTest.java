package com.example.retailapplication.controller;

import com.example.retailapplication.dto.UserDTO;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.exception.RetailApplicationException;
import com.example.retailapplication.exception.UserConflictException;
import com.example.retailapplication.repository.UserRepository;
import com.example.retailapplication.request.UserRequest;
import com.example.retailapplication.util.Response;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Test
    public void login() throws RetailApplicationException {
        // Expected
        UserDTO userDTO = UserDTO.builder()
                .userId(1)
                .username("Apri")
                .password("apri")
                .balance(10000.00)
                .isLoggedIn(true)
                .build();

        Response responseExpected = Response.builder()
                .data(userDTO)
                .build();

        // Actual
        UserRequest userRequest = UserRequest.builder()
                .username("Apri")
                .password("apri")
                .build();

        Response responseActual = userController.login(userRequest);

        // Assertion
        assertEquals(responseExpected.getData(), responseActual.getData());
    }

    @Test
    public void register() throws UserConflictException {
        // Expected
        UserDTO userDTO = UserDTO.builder()
                .userId(4)
                .username("Widianto")
                .password("widianto")
                .balance(0.00)
                .isLoggedIn(false)
                .build();

        Response responseExpected = Response.builder()
                .data(userDTO)
                .build();

        // Actual
        UserRequest userRequest = UserRequest.builder()
                .username("Widianto")
                .password("widianto")
                .build();

        Response responseActual = userController.register(userRequest);

        // Assertion
        assertEquals(responseExpected.getData(), responseActual.getData());
    }

    @Test
    public void getUserList() throws DataNotFoundException {
        // Assertion
        assertNotNull(userController.getUserList());
    }

    /**
     * Start of exception test
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void userNotFoundError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("User not found!");

        // Actual
        UserRequest userRequest = UserRequest.builder()
                .username("Satria")
                .password("satria")
                .build();

        userController.login(userRequest);
    }

    @Test
    public void userAlreadyLoggedInError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("Already logged in");

        // Actual
        UserRequest userRequest = UserRequest.builder()
                .username("Yanti")
                .password("yanti")
                .build();

        userController.login(userRequest);
    }

    @Test
    public void usernameAlreadyExistError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("User with that username already exist!");

        // Actual
        UserRequest userRequest = UserRequest.builder()
                .username("Apri")
                .password("apri")
                .build();

        userController.register(userRequest);
    }

    @Test
    public void userListNotFoundError() throws DataNotFoundException {
        // Expected & Assertion
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("No user found!");

        // Actual
        userRepository.deleteAll();
        userController.getUserList();
    }
}
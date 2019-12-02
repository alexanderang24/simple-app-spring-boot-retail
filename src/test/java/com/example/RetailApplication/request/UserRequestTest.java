package com.example.retailapplication.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRequestTest {

    @Test
    public void transactionRequest() {
        // Expected
        String username = "Widi";
        String password = "widi";

        //Actual
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Widi");
        userRequest.setPassword("widi");

        assertEquals(username, userRequest.getUsername());
        assertEquals(password, userRequest.getPassword());
    }
}
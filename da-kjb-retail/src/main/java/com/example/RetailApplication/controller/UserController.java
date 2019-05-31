package com.example.RetailApplication.controller;

import com.example.RetailApplication.entity.User;
import com.example.RetailApplication.service.UserService;
import com.example.RetailApplication.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
    @PostMapping("/login")
    public ResponseEntity<Response> loginPage(User user) {

        Response response = new Response();
        response.setServiceType(this.getClass().getName());
        response.setResponseCode("Berhasil  Data");
        response.setResponseMessage("Berhasil Membuat Data");
        response.setData(userService.findByUserNameAndPassword(user.getUsername(), user.getPassword()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

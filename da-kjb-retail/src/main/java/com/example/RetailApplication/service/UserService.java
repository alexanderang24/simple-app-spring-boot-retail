package com.example.RetailApplication.service;

import com.example.RetailApplication.entity.User;


public interface UserService {
    User findByUserNameAndPassword(String userId, String password);
}

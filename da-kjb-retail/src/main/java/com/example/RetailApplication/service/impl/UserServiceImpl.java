package com.example.RetailApplication.service.impl;

import com.example.RetailApplication.entity.User;
import com.example.RetailApplication.repository.UserRepository;
import com.example.RetailApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUserNameAndPassword(String userId, String password) {
        return userRepository.findByUsernameAndPassword(userId, password);
    }
}

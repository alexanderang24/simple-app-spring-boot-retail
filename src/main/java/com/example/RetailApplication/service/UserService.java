package com.example.retailapplication.service;

import com.example.retailapplication.dto.UserDTO;
import com.example.retailapplication.entity.User;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.exception.RetailApplicationException;
import com.example.retailapplication.exception.UserConflictException;
import com.example.retailapplication.mapper.UserMapper;
import com.example.retailapplication.repository.UserRepository;
import com.example.retailapplication.util.Response;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to process user related request from UserController
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    /**
     * To login into the application
     * @param username to store username from request
     * @param password to store password from request
     * @return response that store data in the form of user DTO
     * @throws RetailApplicationException with the following detail:
     *      @throws DataNotFoundException when user does not exist in database
     *      @throws UserConflictException when user already logged in before
     */
    public Response<UserDTO> login(String username, String password) throws RetailApplicationException {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            throw new DataNotFoundException("User not found!");
        }

        if (user.getIsLoggedIn()) {
            throw new UserConflictException("Already logged in!");
        }

        user.setIsLoggedIn(true);
        userRepository.save(user);
        UserDTO userDTO = userMapper.userToUserDto(user);

        return Response.<UserDTO>builder()
                .timestamp(LocalDate.now())
                .responseCode(HttpStatus.OK.toString())
                .responseMessage("Successfully logged in.")
                .data(userDTO)
                .build();
    }

    /**
     * To register new user
     * @param username to store username from request
     * @param password to store password from request
     * @return response that store data in the form of user DTO
     * @throws UserConflictException when user has been registered
     */
    public Response<UserDTO> register(String username, String password) throws UserConflictException{
        if(userRepository.findByUsername(username) != null){
            throw new UserConflictException("User with that username already exist!");
        }

        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .password(password)
                .balance(0.00)
                .isLoggedIn(false)
                .build();

        User user = userMapper.userDtoToUser(userDTO);
        userRepository.save(user);
        userDTO.setUserId(user.getUserId());

        return Response.<UserDTO>builder()
                .timestamp(LocalDate.now())
                .responseCode(HttpStatus.OK.toString())
                .responseMessage("Successfully created new user.")
                .data(userDTO)
                .build();
    }

    /**
     * To get all user data
     * @return response that store data in the form of user DTO
     * @throws DataNotFoundException when data is empty
     */
    public Response<List<UserDTO>> getUserList() throws DataNotFoundException {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userMapper.userListToUserDtoList(userList);

        if (userDTOList.isEmpty()) {
            throw new DataNotFoundException("No user found!");
        }

        return Response.<List<UserDTO>>builder()
                .timestamp(LocalDate.now())
                .responseCode(HttpStatus.OK.toString())
                .responseMessage("Successfully display user list.")
                .data(userDTOList)
                .build();
    }

    void save(User user) {
        userRepository.save(user);
    }

    Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }
}

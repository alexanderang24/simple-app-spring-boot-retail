package com.example.RetailApplication.mapper;

import com.example.RetailApplication.dto.UserDTO;
import com.example.RetailApplication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Deprecated
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToDto(User user);
    User userDtoToUser(UserDTO userDTO);
}

package com.example.retailapplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * This class is used to store transaction related request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty(message = "Username is empty!")
    private String username;

    @NotEmpty(message = "Password is empty!")
    private String password;
}

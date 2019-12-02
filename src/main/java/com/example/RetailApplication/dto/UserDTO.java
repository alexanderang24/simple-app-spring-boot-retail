package com.example.retailapplication.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DTO for storing User related request to be parsed into Response
 */
@Data
@Builder
public class  UserDTO {

    @NotNull
    private Integer userId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Double balance;

    @NotNull
    private Boolean isLoggedIn;
}

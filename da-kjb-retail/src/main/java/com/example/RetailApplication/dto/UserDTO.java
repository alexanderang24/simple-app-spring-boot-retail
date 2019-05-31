package com.example.RetailApplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserDTO {

    @NotNull
    @JsonProperty
    private Integer userId;

    @NotNull
    @JsonProperty
    private String username;

    @NotNull
    @JsonProperty
    private String password;

    @JsonProperty
    private Double balance;
}

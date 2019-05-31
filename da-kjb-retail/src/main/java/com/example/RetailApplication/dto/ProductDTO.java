package com.example.RetailApplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ProductDTO {

    @NotNull
    @JsonProperty
    private Integer productId;

    @NotNull
    @JsonProperty
    private String productName;

    @NotNull
    @JsonProperty
    private Integer quantity;
}

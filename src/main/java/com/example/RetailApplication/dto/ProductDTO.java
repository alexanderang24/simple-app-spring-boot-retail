package com.example.retailapplication.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * DTO for storing Product related request to be parsed into Response
 */
@Getter
@Builder
public class ProductDTO {

    @NotNull
    private Integer productId;

    @NotNull
    private String productName;

    @NotNull
    private Integer stock;

    @NotNull
    private Double price;
}

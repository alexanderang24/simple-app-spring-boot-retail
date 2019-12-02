package com.example.retailapplication.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO for storing Transaction related request to be parsed into Response
 */
@Data
@Builder
public class TransactionDTO {

    @NotNull
    private Integer transactionId;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private Integer userId;

    @NotNull
    private String username;

    @NotNull
    private Integer productId;

    @NotNull
    private String productName;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double subtotal;

    @NotNull
    private Double tax;

    @NotNull
    private Double total;
}

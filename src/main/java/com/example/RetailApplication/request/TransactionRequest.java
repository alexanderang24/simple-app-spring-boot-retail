package com.example.retailapplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This class is used to store transaction related request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @Min(value = 1)
    @NotNull(message = "User ID is empty!")
    private Integer userId;

    @Min(value = 1)
    @NotNull(message = "Product ID is empty!")
    private Integer productId;

    @Min(value = 1)
    @NotNull(message = "Item quantity is empty!")
    private Integer quantity;
}

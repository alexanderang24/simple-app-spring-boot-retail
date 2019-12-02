package com.example.retailapplication.controller;

import com.example.retailapplication.dto.TransactionDTO;
import com.example.retailapplication.exception.RetailApplicationException;
import com.example.retailapplication.exception.UserNotLoggedInException;
import com.example.retailapplication.request.TransactionRequest;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.exception.InvalidAmountException;
import com.example.retailapplication.service.TransactionService;
import com.example.retailapplication.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * This class is used to receive transaction related requests
 */
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    /**
     * To get all transaction data
     * @return response that store data in the form of transaction DTO list
     * @throws DataNotFoundException when data is empty
     */
    @PostMapping("/transactionlist")
    public Response getTransactionList() throws DataNotFoundException {
        return transactionService.getTransactionList();
    }

    /**
     * To receive checkout request when user is buying products
     * @param transactionRequest to store request
     * @return response that store data in the form of transaction DTO
     * @throws RetailApplicationException with the following details:
         * @throws InvalidAmountException when user balance is not enough, stock is not enough or empty, or invalid request quantity
         * @throws DataNotFoundException when user or product data is empty
         * @throws UserNotLoggedInException when user is not logged when trying to checkout
     */
    @PostMapping("/checkout")
    public Response<TransactionDTO> checkout(@RequestBody @Valid TransactionRequest transactionRequest) throws RetailApplicationException {
        return transactionService.checkout(transactionRequest.getUserId(), transactionRequest.getProductId(), transactionRequest.getQuantity());
    }
}

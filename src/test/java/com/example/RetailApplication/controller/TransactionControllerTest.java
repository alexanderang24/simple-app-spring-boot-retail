package com.example.retailapplication.controller;

import com.example.retailapplication.dto.TransactionDTO;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.exception.InvalidAmountException;
import com.example.retailapplication.exception.RetailApplicationException;
import com.example.retailapplication.exception.UserNotLoggedInException;
import com.example.retailapplication.repository.TransactionRepository;
import com.example.retailapplication.request.TransactionRequest;
import com.example.retailapplication.util.Response;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {

    @Autowired
    TransactionController transactionController;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void getTransactionList() throws DataNotFoundException {
        // Assertion
        assertNotNull(transactionController.getTransactionList());
    }

    @Test
    public void checkout() throws RetailApplicationException {
        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(3)
                .productId(1)
                .quantity(1)
                .build();

        Response<TransactionDTO> responseActual = transactionController.checkout(transactionRequest);

        TransactionDTO temp = responseActual.getData();

        // Expected
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .transactionId(temp.getTransactionId())
                .transactionDate(temp.getTransactionDate())
                .userId(3)
                .username("Yanti")
                .productId(1)
                .productName("Kaos kaki Wonder Woman")
                .price(15000.00)
                .quantity(1)
                .subtotal(15000.00)
                .tax(1500.00)
                .total(16500.00)
                .build();

        Response responseExpected = Response.builder()
                .data(transactionDTO)
                .build();

        // Assertion
        assertEquals(responseExpected.getData(), responseActual.getData());
    }

    /**
     * Start of exception test
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void transactionNotFoundError() throws DataNotFoundException {
        // Expected & Assertion
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("No transaction found!");

        // Actual
        transactionRepository.deleteAll();
        transactionController.getTransactionList();
    }

    @Test
    public void insufficientUserBalanceError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(InvalidAmountException.class);
        expectedException.expectMessage("Insufficient user balance!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(2)
                .productId(2)
                .quantity(10)
                .build();

        transactionController.checkout(transactionRequest);
    }

    @Test
    public void emptyProductStockError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(InvalidAmountException.class);
        expectedException.expectMessage("Product stock is empty!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(3)
                .productId(1)
                .quantity(5)
                .build();

        // Checkout to empty product stock
        transactionController.checkout(transactionRequest);

        // Checkout to trigger exception
        transactionController.checkout(transactionRequest);
    }

    @Test
    public void insufficientProductStockError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(InvalidAmountException.class);
        expectedException.expectMessage("Stock is not enough!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(3)
                .productId(1)
                .quantity(10)
                .build();

        transactionController.checkout(transactionRequest);
    }

    @Test
    public void userNotFoundError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("User not found!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(4)
                .productId(1)
                .quantity(1)
                .build();

        transactionController.checkout(transactionRequest);
    }

    @Test
    public void userNotLoggedInError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(UserNotLoggedInException.class);
        expectedException.expectMessage("User is not logged in or session is expired!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(1)
                .productId(1)
                .quantity(1)
                .build();

        transactionController.checkout(transactionRequest);
    }

    @Test
    public void productNotFoundError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("Product not found!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(3)
                .productId(4)
                .quantity(1)
                .build();

        transactionController.checkout(transactionRequest);
    }

    @Test
    public void invalidQuantityError() throws RetailApplicationException {
        // Expected & Assertion
        expectedException.expect(InvalidAmountException.class);
        expectedException.expectMessage("Quantity must be larger than 0!");

        // Actual
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .userId(3)
                .productId(1)
                .quantity(0)
                .build();

        transactionController.checkout(transactionRequest);
    }
}
package com.example.retailapplication.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionRequestTest {

    @Test
    public void transactionRequest() {
        // Expected
        Integer userId = 1;
        Integer productId = 1;
        Integer quantity = 1;

        //Actual
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setUserId(1);
        transactionRequest.setProductId(1);
        transactionRequest.setQuantity(1);

        assertEquals(userId, transactionRequest.getUserId());
        assertEquals(productId, transactionRequest.getProductId());
        assertEquals(quantity, transactionRequest.getQuantity());
    }
}
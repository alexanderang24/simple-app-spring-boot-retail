package com.example.retailapplication.controller;

import com.example.retailapplication.dto.ProductDTO;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.service.ProductService;
import com.example.retailapplication.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is used to receive product related requests
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * To get all product data
     * @return response that store data in the form of product DTO
     * @throws DataNotFoundException when data is empty
     */
    @PostMapping("/productlist")
    public Response<List<ProductDTO>> getProductList() throws DataNotFoundException {
        return productService.getProductList();
    }

}

package com.example.RetailApplication.service;

import com.example.RetailApplication.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> getProductList();
}

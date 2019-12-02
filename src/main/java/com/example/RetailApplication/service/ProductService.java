package com.example.retailapplication.service;

import com.example.retailapplication.dto.ProductDTO;
import com.example.retailapplication.entity.Product;
import com.example.retailapplication.exception.DataNotFoundException;
import com.example.retailapplication.mapper.ProductMapper;
import com.example.retailapplication.repository.ProductRepository;
import com.example.retailapplication.util.Response;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to process product related request from ProductController
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    /**
     * To get all product data
     * @return response that store data in the form of product DTO
     * @throws DataNotFoundException when data is empty
     */
    public Response<List<ProductDTO>> getProductList() throws DataNotFoundException {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productMapper.productListToProductDtoList(productList);

        if (productDTOList.isEmpty()) {
            throw new DataNotFoundException("No product found!");
        }

        return Response.<List<ProductDTO>>builder()
                .timestamp(LocalDate.now())
                .responseCode(HttpStatus.OK.toString())
                .responseMessage("Successfully display product list.")
                .data(productDTOList)
                .build();
    }

    void save(Product product) {
        productRepository.save(product);
    }

    Optional<Product> findById(Integer productId) {
        return productRepository.findById(productId);
    }
}

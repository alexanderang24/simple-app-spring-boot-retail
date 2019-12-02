package com.example.retailapplication.mapper;

import com.example.retailapplication.dto.ProductDTO;
import com.example.retailapplication.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This class is used to map Product entity to Product DTO and vice versa
 */
@Mapper
public interface ProductMapper {

    /**
     * Map Product entity into Product DTO
     * @param product entity to be mapped
     * @return Product DTO
     */
    ProductDTO productToProductDto(Product product);

    /**
     * Map List<Product> into List<ProductDTO>
     * @param product list of product entity to be mapped
     * @return list of Product DTO
     */
    List<ProductDTO> productListToProductDtoList(List<Product> product);
}

package com.example.RetailApplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private String productName;

    @Column
    private Integer quantity;
}

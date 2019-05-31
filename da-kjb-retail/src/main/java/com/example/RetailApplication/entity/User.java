package com.example.RetailApplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private Double balance;
}

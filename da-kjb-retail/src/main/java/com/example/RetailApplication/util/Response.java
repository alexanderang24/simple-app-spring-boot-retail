package com.example.RetailApplication.util;

import lombok.Data;

/**
 * @param <T> for data
 */
@Data
public class Response<T> {
    private String serviceType;
    private String responseCode;
    private String responseMessage;
    private T data;
}

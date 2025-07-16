package com.example.payment_service.exception;

public class OrderServiceException extends RuntimeException {
    // Constructeur avec message
    public OrderServiceException(String message) {
        super(message);
    }

    // Constructeur avec message ET cause (obligatoire pour votre cas)
    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
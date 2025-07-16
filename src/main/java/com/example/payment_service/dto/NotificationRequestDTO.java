package com.example.payment_service.dto;

import lombok.Data;

@Data
public class NotificationRequestDTO {
    private String type;
    private NotificationDataDTO data;

    @Data
    public static class NotificationDataDTO {
        private String email;
        private String customerName;
        private double amount;
        private String transactionId;
        private String paymentMethod;
        private String referenceNumber;
        private String orderNumber;
        private String description;
    }
}
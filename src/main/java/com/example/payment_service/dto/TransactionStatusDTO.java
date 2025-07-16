package com.example.payment_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionStatusDTO {
    private String transactionId;
    private String statut;
}
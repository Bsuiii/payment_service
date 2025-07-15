package com.example.payment_service.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserTransactionDTO {
    private String transactionId;
    private String commandeId;
    private Double montant;
    private LocalDateTime date;
    private String statut;
}
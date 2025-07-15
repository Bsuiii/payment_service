package com.example.payment_service.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDTO {
    private String transactionId;
    private String commandeId;
    private String userId;
    private Double montant;
    private String statut;
    private String modePaiement;
    private LocalDateTime date;
}
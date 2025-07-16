package com.example.payment_service.dto;

import lombok.Data;

@Data
public class OrderCancelDTO {
    private String reason;          // Raison de l'échec (ex: "Paiement refusé")
}
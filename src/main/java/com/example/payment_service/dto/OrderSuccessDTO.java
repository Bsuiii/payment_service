package com.example.payment_service.dto;

import lombok.Data;

@Data
public class OrderSuccessDTO {
    private String paymentId;       // ID de la transaction (ex: "TXN_789012345")
    private String paymentMethod;   // Méthode de paiement (ex: "carte", "paypal")
}
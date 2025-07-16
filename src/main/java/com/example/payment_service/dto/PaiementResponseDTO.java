package com.example.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementResponseDTO {
    private Long id;               // Devrait être String pour transactionId
    private String typePaiement;   // À renommer en modePaiement
    private Double montant;
    private String statut;
    private String moyenPaiement;  // À fusionner avec typePaiement
    private Long commandeId;       // Devrait être String
}
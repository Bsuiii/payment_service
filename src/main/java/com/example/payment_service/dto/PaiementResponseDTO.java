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
    private Long id;
    private String typePaiement;
    private Double montant;
    private String statut;
    private String moyenPaiement;
    private Long commandeId;
}

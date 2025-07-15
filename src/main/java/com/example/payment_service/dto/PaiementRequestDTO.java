package com.example.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementRequestDTO {
    @NotBlank
    private String typePaiement;

    @NotNull
    private Double montant;

    @NotBlank
    private String statut;

    @NotBlank
    private String moyenPaiement;

    @NotNull
    private Long commandeId;
}

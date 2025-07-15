package com.example.payment_service.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


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

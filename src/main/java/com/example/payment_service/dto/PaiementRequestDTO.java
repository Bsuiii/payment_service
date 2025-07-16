package com.example.payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaiementRequestDTO {
    @NotBlank
    private String commandeId;

    @NotBlank
    private String userId;

    @NotNull
    private Double montant;

    @NotBlank
    private String modePaiement;
}
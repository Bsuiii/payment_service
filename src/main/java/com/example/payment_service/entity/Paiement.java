package com.example.payment_service.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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



package com.example.payment_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {
    @Id
    private String transactionId;

    @Column(name = "commande_id", nullable = false)
    private String commandeId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private Double montant;

    // Unifiez soit sur mode_paiement soit type_paiement
    @Column(name = "mode_paiement", nullable = false)
    private String modePaiement;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private LocalDateTime date;
}
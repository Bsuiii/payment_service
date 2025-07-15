package com.example.payment_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {
    @Id
    private String transactionId;

    private String commandeId;
    private Double montant;
    private String statut;
    private String modePaiement;
    private LocalDateTime date;
    private String userId; // Pour l'historique utilisateur
}
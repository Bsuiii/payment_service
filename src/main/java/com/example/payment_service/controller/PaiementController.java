package com.example.payment_service.controller;

import com.example.payment_service.dto.*;
import com.example.payment_service.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiement")
@RequiredArgsConstructor
@Tag(name = "Paiement", description = "API de gestion des paiements")
public class PaiementController {

    private final PaiementService service;

    @PostMapping
    @Operation(summary = "Initier un paiement")
    public ResponseEntity<TransactionResponseDTO> processPayment(
            @RequestBody @Valid PaiementRequestDTO dto) {
        return new ResponseEntity<>(service.processPayment(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "Vérifier le statut d'un paiement")
    public ResponseEntity<TransactionStatusDTO> getPaymentStatus(
            @PathVariable String transactionId) {
        return ResponseEntity.ok(service.getPaymentStatus(transactionId));
    }

    @DeleteMapping("/{transactionId}")
    @Operation(summary = "Supprimer une transaction (test)")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable String transactionId) {
        service.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remboursement/{transactionId}")
    @Operation(summary = "Rembourser un paiement")
    public ResponseEntity<TransactionResponseDTO> processRefund(
            @PathVariable String transactionId) {
        return ResponseEntity.ok(service.processRefund(transactionId));
    }

    @GetMapping("/utilisateur/transactions/{userId}")
    @Operation(summary = "Historique des paiements d'un utilisateur")
    public ResponseEntity<List<UserTransactionDTO>> getUserPaymentHistory(
            @PathVariable String userId) {
        return ResponseEntity.ok(service.getUserPaymentHistory(userId));
    }

    @GetMapping("/transaction/{commandeId}")
    @Operation(summary = "Rechercher une transaction par commande")
    public ResponseEntity<TransactionResponseDTO> getPaymentByOrder(
            @PathVariable String commandeId) {
        return ResponseEntity.ok(service.getPaymentByOrder(commandeId));
    }

    @GetMapping("/modes")
    @Operation(summary = "Modes de paiement disponibles")
    public ResponseEntity<List<String>> getPaymentMethods() {
        return ResponseEntity.ok(service.getAvailablePaymentMethods());
    }

    @GetMapping("/stats/globales")
    @Operation(summary = "Statistiques globales de paiement")
    public ResponseEntity<PaymentStatsDTO> getGlobalStats() {
        return ResponseEntity.ok(service.getGlobalStats());
    }

    @GetMapping("/transactions/recentes")
    @Operation(summary = "Liste des paiements récents")
    public ResponseEntity<List<TransactionResponseDTO>> getRecentTransactions() {
        return ResponseEntity.ok(service.getRecentTransactions());
    }
}
package com.example.payment_service.service;

import com.example.payment_service.dto.PaiementRequestDTO;
import com.example.payment_service.dto.PaiementResponseDTO;
import java.util.List;

public interface PaiementService {
    // Méthodes de base
    PaiementResponseDTO createPaiement(PaiementRequestDTO dto);
    List<PaiementResponseDTO> getAllPaiements();
    PaiementResponseDTO getPaiementById(Long id);
    PaiementResponseDTO updatePaiement(Long id, PaiementRequestDTO dto);
    void deletePaiement(Long id);

    // Méthodes spécifiques au contrat OpenAPI
    PaiementResponseDTO processPayment(PaiementRequestDTO dto);
    PaiementResponseDTO getPaymentStatus(String transactionId);
    PaiementResponseDTO processRefund(String transactionId);
    List<PaiementResponseDTO> getUserPaymentHistory(String userId);
    PaiementResponseDTO getPaymentByOrder(String commandeId);
    List<String> getAvailablePaymentMethods();
    Map<String, Object> getGlobalStats();
    List<PaiementResponseDTO> getRecentTransactions();
}
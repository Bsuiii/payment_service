package com.example.payment_service.service;

import com.example.payment_service.dto.*;
import com.example.payment_service.entity.Paiement;
import com.example.payment_service.exception.PaymentNotFoundException;
import com.example.payment_service.exception.PaymentProcessingException;
import com.example.payment_service.repository.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;  // Import manquant ajouté

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository repository;

    @Override
    public TransactionResponseDTO processPayment(PaiementRequestDTO dto) {
        validatePaymentRequest(dto);

        Paiement paiement = Paiement.builder()
                .transactionId(UUID.randomUUID().toString())
                .commandeId(dto.getCommandeId())
                .userId(dto.getUserId())
                .montant(dto.getMontant())
                .modePaiement(dto.getModePaiement())
                .statut(generateRandomStatus())
                .date(LocalDateTime.now())
                .build();

        Paiement savedPayment = repository.save(paiement);
        return mapToTransactionResponse(savedPayment);
    }

    @Override
    public TransactionStatusDTO getPaymentStatus(String transactionId) {
        Paiement paiement = repository.findById(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException("Transaction introuvable"));

        return TransactionStatusDTO.builder()
                .transactionId(paiement.getTransactionId())
                .statut(paiement.getStatut())
                .build();
    }

    @Override
    public void deleteTransaction(String transactionId) {
        if (!repository.existsById(transactionId)) {
            throw new PaymentNotFoundException("Transaction introuvable");
        }
        repository.deleteById(transactionId);
    }

    @Override
    public TransactionResponseDTO processRefund(String transactionId) {
        Paiement paiement = repository.findById(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException("Transaction introuvable"));

        if (!"succès".equals(paiement.getStatut())) {
            throw new PaymentProcessingException("Seuls les paiements réussis peuvent être remboursés");
        }

        paiement.setStatut("remboursé");
        Paiement updatedPayment = repository.save(paiement);

        return mapToTransactionResponse(updatedPayment);
    }

    @Override
    public List<UserTransactionDTO> getUserPaymentHistory(String userId) {
        return repository.findByUserId(userId).stream()
                .map(this::mapToUserTransactionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDTO getPaymentByOrder(String commandeId) {
        Paiement paiement = repository.findByCommandeId(commandeId)
                .orElseThrow(() -> new PaymentNotFoundException("Aucune transaction trouvée pour cette commande"));

        return mapToTransactionResponse(paiement);
    }

    @Override
    public List<String> getAvailablePaymentMethods() {
        return List.of("carte", "paypal");
    }

    @Override
    public PaymentStatsDTO getGlobalStats() {
        List<Paiement> allPayments = repository.findAll();

        long successCount = allPayments.stream()
                .filter(p -> "succès".equals(p.getStatut()))
                .count();

        double totalAmount = allPayments.stream()
                .filter(p -> "succès".equals(p.getStatut()))
                .mapToDouble(Paiement::getMontant)
                .sum();

        Map<String, Long> paymentsByMethod = allPayments.stream()
                .collect(Collectors.groupingBy(
                        Paiement::getModePaiement,
                        Collectors.counting()
                ));

        return PaymentStatsDTO.builder()
                .nombreTransactions(allPayments.size())
                .transactionsReussies(successCount)
                .totalEncaissé(totalAmount)
                .parMode(paymentsByMethod)
                .build();
    }

    @Override
    public List<TransactionResponseDTO> getRecentTransactions() {
        return repository.findTop10ByOrderByDateDesc().stream()
                .map(this::mapToTransactionResponse)
                .collect(Collectors.toList());
    }

    private String generateRandomStatus() {
        return Math.random() > 0.2 ? "succès" : "échec";
    }

    private void validatePaymentRequest(PaiementRequestDTO dto) {
        if (dto.getMontant() == null || dto.getMontant() <= 0) {
            throw new PaymentProcessingException("Le montant doit être positif");
        }

        if (!getAvailablePaymentMethods().contains(dto.getModePaiement())) {
            throw new PaymentProcessingException("Mode de paiement non supporté: " + dto.getModePaiement());
        }
    }

    private TransactionResponseDTO mapToTransactionResponse(Paiement paiement) {
        return TransactionResponseDTO.builder()
                .transactionId(paiement.getTransactionId())
                .commandeId(paiement.getCommandeId())
                .userId(paiement.getUserId())
                .montant(paiement.getMontant())
                .statut(paiement.getStatut())
                .modePaiement(paiement.getModePaiement())
                .date(paiement.getDate())
                .build();
    }

    private UserTransactionDTO mapToUserTransactionDTO(Paiement paiement) {
        return UserTransactionDTO.builder()
                .transactionId(paiement.getTransactionId())
                .commandeId(paiement.getCommandeId())
                .montant(paiement.getMontant())
                .statut(paiement.getStatut())
                .date(paiement.getDate())
                .build();
    }
}
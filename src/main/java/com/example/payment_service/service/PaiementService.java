package com.example.payment_service.service;

import com.example.payment_service.dto.*;
import java.util.List;
import java.util.Map;

public interface PaiementService {
    TransactionResponseDTO processPayment(PaiementRequestDTO dto);
    TransactionStatusDTO getPaymentStatus(String transactionId);
    void deleteTransaction(String transactionId);
    TransactionResponseDTO processRefund(String transactionId);
    List<UserTransactionDTO> getUserPaymentHistory(String userId);
    TransactionResponseDTO getPaymentByOrder(String commandeId);
    List<String> getAvailablePaymentMethods();
    PaymentStatsDTO getGlobalStats();
    List<TransactionResponseDTO> getRecentTransactions();
}
package com.example.payment_service.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class PaymentStatsDTO {
    private int nombreTransactions;
    private long transactionsReussies; // Changé de int à long
    private double totalEncaissé;
    private Map<String, Long> parMode;
}
package com.example.payment_service.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class PaymentStatsDTO {
    private int nombreTransactions;
    private double totalEncaissé;
    private Map<String, Long> parMode;
}
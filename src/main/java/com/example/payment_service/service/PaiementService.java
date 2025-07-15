package com.example.payment_service.service;

import com.example.payment_service.dto.PaiementRequestDTO;
import com.example.payment_service.dto.PaiementResponseDTO;

import java.util.List;

public interface PaiementService {
    PaiementResponseDTO createPaiement(PaiementRequestDTO dto);
    List<PaiementResponseDTO> getAllPaiements();
    PaiementResponseDTO getPaiementById(Long id);
    PaiementResponseDTO updatePaiement(Long id, PaiementRequestDTO dto);
    void deletePaiement(Long id);
}

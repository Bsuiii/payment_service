package com.example.payment_service.service;

import com.example.payment_service.dto.PaiementRequestDTO;
import com.example.payment_service.dto.PaiementResponseDTO;
import com.example.payment_service.entity.Paiement;
import com.example.payment_service.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaiementServiceImpl implements PaiementService {

    @Autowired
    private  PaiementRepository repository;

    @Override
    public PaiementResponseDTO createPaiement(PaiementRequestDTO dto) {
        Paiement paiement = Paiement.builder()
            .typePaiement(dto.getTypePaiement())
            .montant(dto.getMontant())
            .statut(dto.getStatut())
            .moyenPaiement(dto.getMoyenPaiement())
            .commandeId(dto.getCommandeId())
            .build();
        Paiement saved = repository.save(paiement);
        return toDTO(saved);
    }

    @Override
    public List<PaiementResponseDTO> getAllPaiements() {
        return repository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public PaiementResponseDTO getPaiementById(Long id) {
        Paiement paiement = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paiement not found"));
        return toDTO(paiement);
    }

    @Override
    public PaiementResponseDTO updatePaiement(Long id, PaiementRequestDTO dto) {
        Paiement paiement = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paiement not found"));
        paiement.setTypePaiement(dto.getTypePaiement());
        paiement.setMontant(dto.getMontant());
        paiement.setStatut(dto.getStatut());
        paiement.setMoyenPaiement(dto.getMoyenPaiement());
        paiement.setCommandeId(dto.getCommandeId());
        Paiement updated = repository.save(paiement);
        return toDTO(updated);
    }

    @Override
    public void deletePaiement(Long id) {
        repository.deleteById(id);
    }

    private PaiementResponseDTO toDTO(Paiement paiement) {
        return PaiementResponseDTO.builder()
            .id(paiement.getId())
            .typePaiement(paiement.getTypePaiement())
            .montant(paiement.getMontant())
            .statut(paiement.getStatut())
            .moyenPaiement(paiement.getMoyenPaiement())
            .commandeId(paiement.getCommandeId())
            .build();
    }
}

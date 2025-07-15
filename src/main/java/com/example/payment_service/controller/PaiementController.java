package com.example.payment_service.controller;

import com.example.payment_service.dto.PaiementRequestDTO;
import com.example.payment_service.dto.PaiementResponseDTO;
import com.example.payment_service.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiement", description = "Operations liées aux paiements")
public class PaiementController {

    @Autowired
    private  PaiementService service;

    @PostMapping
    @Operation(summary = "Créer un paiement")
    public ResponseEntity<PaiementResponseDTO> create(@RequestBody @Valid PaiementRequestDTO dto) {
        return new ResponseEntity<>(service.createPaiement(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    public ResponseEntity<List<PaiementResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllPaiements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un paiement par ID")
    public ResponseEntity<PaiementResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaiementById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un paiement")
    public ResponseEntity<PaiementResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid PaiementRequestDTO dto) {
        return ResponseEntity.ok(service.updatePaiement(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}

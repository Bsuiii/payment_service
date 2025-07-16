package com.example.payment_service.repository;

import com.example.payment_service.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PaiementRepository extends JpaRepository<Paiement, String> {
    Optional<Paiement> findByCommandeId(String commandeId);
    List<Paiement> findByUserId(String userId);

    @Query("SELECT p FROM Paiement p ORDER BY p.date DESC LIMIT 10")
    List<Paiement> findTop10ByOrderByDateDesc();
}
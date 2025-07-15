package com.example.payment_service.dto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementRequestDTO {
    @NotBlank
    private String typePaiement;

    @NotNull
    private Double montant;

    @NotBlank
    private String statut;

    @NotBlank
    private String moyenPaiement;

    @NotNull
    private Long commandeId;
}

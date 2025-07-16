package com.example.payment_service.service;

import com.example.payment_service.dto.OrderCancelDTO;
import com.example.payment_service.dto.OrderSuccessDTO;
import com.example.payment_service.exception.OrderServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceClient {
    // 1. Ajoutez les imports et le logger en haut de la classe
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceClient.class);

    @Value("${order.service.url}")
    private String orderServiceUrl;

    private final RestTemplate restTemplate;

    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 2. Modifiez la méthode confirmOrder
    public void confirmOrder(String orderId, OrderSuccessDTO successDTO) {
        try {
            String url = orderServiceUrl + "/orders/" + orderId;
            HttpEntity<OrderSuccessDTO> request = new HttpEntity<>(successDTO, createHeaders());

            ResponseEntity<Void> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Void.class);

            logger.info("Commande {} confirmée avec succès. Réponse: {}", orderId, response.getStatusCode());
        } catch (Exception e) {
            logger.error("Échec de la confirmation de la commande {}: {}", orderId, e.getMessage());
            throw new OrderServiceException("Erreur lors de la confirmation de la commande", e);
        }
    }

    // 3. Modifiez la méthode cancelOrder (optionnel)
    public void cancelOrder(String orderId, OrderCancelDTO cancelDTO) {
        try {
            String url = orderServiceUrl + "/orders/" + orderId + "/cancel";
            HttpEntity<OrderCancelDTO> request = new HttpEntity<>(cancelDTO, createHeaders());

            ResponseEntity<Void> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Void.class);

            logger.info("Commande {} annulée avec succès. Réponse: {}", orderId, response.getStatusCode());
        } catch (Exception e) {
            logger.error("Échec de l'annulation de la commande {}: {}", orderId, e.getMessage());
            throw new OrderServiceException("Erreur lors de l'annulation de la commande", e);
        }
    }

    // 4. Ajoutez la méthode utilitaire createHeaders
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
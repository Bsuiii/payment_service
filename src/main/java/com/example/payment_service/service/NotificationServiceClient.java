package com.example.payment_service.service;

import com.example.payment_service.dto.NotificationRequestDTO;
import com.example.payment_service.exception.NotificationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceClient.class);

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    private final RestTemplate restTemplate;

    public NotificationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendPaymentSuccessNotification(NotificationRequestDTO notificationRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(notificationRequest, headers);

        logger.info("Attempting to send notification for transaction: {}",
                notificationRequest.getData().getTransactionId());
        logger.debug("Notification payload: {}", notificationRequest);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    notificationServiceUrl + "/notify",
                    request,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.error("Notification failed for transaction {}. Status: {}, Response: {}",
                        notificationRequest.getData().getTransactionId(),
                        response.getStatusCodeValue(),
                        response.getBody());
                throw new NotificationFailedException("Failed to send notification: " + response.getBody());
            }

            logger.info("Notification successfully sent for transaction: {}. Service response: {}",
                    notificationRequest.getData().getTransactionId(),
                    response.getBody());

        } catch (Exception e) {
            logger.error("Exception while sending notification for transaction {}: {}",
                    notificationRequest.getData().getTransactionId(),
                    e.getMessage(), e);
            throw new NotificationFailedException("Exception while sending notification", e);
        }
    }
}
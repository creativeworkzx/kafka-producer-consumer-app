package com.kafka.app.controller;

import org.springframework.web.bind.annotation.*;

import com.kafka.app.service.KafkaProducerService;

/**
 * REST Controller for Kafka operations.
 * Provides an endpoint to send messages to a Kafka topic.
 */
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    // Kafka producer service for handling message publishing
    private final KafkaProducerService kafkaProducerService;

    /**
     * Constructor-based dependency injection for KafkaProducerService.
     *
     * @param kafkaProducerService the service responsible for producing Kafka messages
     */
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * Endpoint to send a message to the Kafka topic `SOURCE_TOPIC`.
     *
     * @param message The message content to be sent to Kafka, provided in the request body.
     * @return A success message indicating that the message was sent.
     */
    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        // Sends the message to the Kafka producer service
        kafkaProducerService.sendMessage(message);
        // Returns a confirmation message upon successful sending
        return "Message sent to SOURCE_TOPIC";
    }
}

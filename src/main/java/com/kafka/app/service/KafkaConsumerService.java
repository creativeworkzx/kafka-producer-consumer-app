package com.kafka.app.service;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service to consume and process messages from Kafka topics. Consumes messages
 * from three topics: SOURCE_TOPIC, EVEN_TOPIC, and ODD_TOPIC, and logs the
 * processed messages.
 */
@Service
public class KafkaConsumerService {

	// KafkaTemplate instance for sending messages to Kafka if needed
	private final KafkaTemplate<String, String> kafkaTemplate;

	// ObjectMapper instance for JSON processing
	private final ObjectMapper objectMapper = new ObjectMapper();

	// Logger instance for logging invalid data and errors
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	/**
	 * Constructor to initialize KafkaConsumerService with KafkaTemplate.
	 * 
	 * @param kafkaTemplate KafkaTemplate for interacting with Kafka topics.
	 */
	public KafkaConsumerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * Kafka listener for the SOURCE_TOPIC. Processes messages received from
	 * SOURCE_TOPIC and logs them.
	 *
	 * @param record The Kafka record containing the message from SOURCE_TOPIC.
	 */
	@KafkaListener(topics = "SOURCE_TOPIC", groupId = "kafka-age-process-group")
	public void consumeMessage(ConsumerRecord<String, String> record) {
		try {
			// Log and process the message from SOURCE_TOPIC
			logMessage(record, "SOURCE_TOPIC");
		} catch (Exception e) {
			// Log error if message processing fails
			logger.error("Error processing message: {} ", e.getMessage());
		}
	}

	/**
	 * Kafka listener for the EVEN_TOPIC. Processes messages received from
	 * EVEN_TOPIC and logs them.
	 *
	 * @param record The Kafka record containing the message from EVEN_TOPIC.
	 */
	@KafkaListener(topics = "EVEN_TOPIC", groupId = "kafka-age-process-group")
	public void consumeEvenMessage(ConsumerRecord<String, String> record) {
		try {
			// Log and process the message from EVEN_TOPIC
			logMessage(record, "EVEN_TOPIC");
		} catch (Exception e) {
			// Log error if message processing fails
			logger.error("Error processing message: {} ", e.getMessage());
		}
	}

	/**
	 * Kafka listener for the ODD_TOPIC. Processes messages received from ODD_TOPIC
	 * and logs them.
	 *
	 * @param record The Kafka record containing the message from ODD_TOPIC.
	 */
	@KafkaListener(topics = "ODD_TOPIC", groupId = "kafka-age-process-group")
	public void consumeOddMessage(ConsumerRecord<String, String> record) {
		try {
			// Log and process the message from ODD_TOPIC
			logMessage(record, "ODD_TOPIC");
		} catch (Exception e) {
			// Log error if message processing fails
			logger.error("Error processing message: {} ", e.getMessage());
		}
	}

	/**
	 * Helper method to log a Kafka message after parsing it from JSON. Converts the
	 * JSON string to a Map and logs its contents.
	 *
	 * @param record The Kafka record containing the JSON message.
	 * @param topic  The topic from which the message was consumed.
	 * @throws JsonMappingException    If there is an error mapping the JSON to a
	 *                                 Map.
	 * @throws JsonProcessingException If there is a JSON processing error.
	 */
	public void logMessage(ConsumerRecord<String, String> record, String topic) throws Exception {
		// Extract the message content from the Kafka record
		String message = record.value();

		// Convert the JSON message to a Map for easier data access and logging
		Map<String, Object> data = objectMapper.readValue(message, Map.class);

		// Log the processed message along with its data
		logger.info("Processed message from Topic: {} Data: {}", topic, data);
	}
}

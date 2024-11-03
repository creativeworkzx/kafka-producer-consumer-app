markdown

# Kafka Test Application

This application tests a use case where messages from a source Kafka topic (`SOURCE_TOPIC`) are consumed, processed based on an age calculation, and routed to either `EVEN_TOPIC` or `ODD_TOPIC` based on the result.

---

## Setup Instructions

### 1. Clone the Repository
Clone this repository to your local machine:
git clone https://github.com/creativeworkzx/kafka-producer-consumer-app.git
feature Branch: feature/kafkatest

###Build and Run the Spring Boot Application
Start the application using:

./mvnw spring-boot:run
3. Send a Test Message
Use an HTTP client or curl to send a sample message to the /api/kafka/send endpoint:


curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","address":"123 Street","dateOfBirth":"1990-01-01"}' http://localhost:8080/api/kafka/send
### Code Structure Overview
KafkaController

Located in com.kafka.app.controller, this REST controller exposes an endpoint to send messages to SOURCE_TOPIC.
KafkaProducerService

Located in com.kafka.app.service, this service handles publishing messages to Kafka's SOURCE_TOPIC.
KafkaConsumerService

Located in com.kafka.app.service, this service listens to SOURCE_TOPIC, EVEN_TOPIC, and ODD_TOPIC. Messages are processed based on the age calculation and routed to EVEN_TOPIC or ODD_TOPIC depending on whether the age is even or odd by "Kafka-Pipeline-Processor" . Logs indicate from which topic each message was received.

application.properties
Contains application configurations, including Kafka broker URLs, topic names, and consumer group IDs.

### Testing Instructions
1. Start the Processor Applications
Start both the main application (kafka-producer-consumer-app) and the Kafka processor service (kafka-pipeline-processor).

2. Send a Message
Send a test message to SOURCE_TOPIC through the /api/kafka/send endpoint (see example in Setup Instructions).

3. Message Routing and Verification
The kafka-pipeline-processor application consumes messages from SOURCE_TOPIC, calculates age, and routes the message to either EVEN_TOPIC or ODD_TOPIC based on the age.
The kafka-producer-consumer-app application consumes messages from SOURCE_TOPIC, EVEN_TOPIC, and ODD_TOPIC, logging the topic source and message content to verify correct routing.
4. Check Logs
Confirm the correct routing by viewing logs. The logs will display messages received from each topic (SOURCE_TOPIC, EVEN_TOPIC, and ODD_TOPIC).

This setup verifies the application's routing logic based on age and allows for easy testing and debugging of Kafka message flows.
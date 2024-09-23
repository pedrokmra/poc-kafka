package com.pedrok.pockafka.notification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrok.pockafka.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class OrderConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic.order}", groupId = "${spring.kafka.group}")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            log.info("Consumed message: key = {}, value = {}", record.key(), record.value());

            var order = objectMapper.convertValue(record.value(), Order.class);

            notify(order);

            acknowledgment.acknowledge();
        } catch (Exception exception) {
            log.error("Error processing message: {}", record.value(), exception);
            throw new RuntimeException(exception);
        }
    }

    // TODO create service
    private void notify(Order order) {
        log.info("NOTIFICATION: {}", order);
    }
}

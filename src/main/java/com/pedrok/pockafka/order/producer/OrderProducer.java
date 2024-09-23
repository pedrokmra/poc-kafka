package com.pedrok.pockafka.order.producer;

import com.pedrok.pockafka.order.model.Order;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final Environment environment;

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        Order order = Order.builder()
                .id("12345")
                .customerId("customerId")
                .amount(new BigDecimal("99.99"))
                .status(Order.OrderStatusEnum.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        send(order);
    }

    public void send(Order message) {
        try {
            kafkaTemplate.send(environment.getProperty("spring.kafka.topic.order"), message);
            log.info("Sent message: {}: ", message);
        } catch (Exception exception) {
            log.error("Error processing message: {}", message, exception);
        }
    }
}

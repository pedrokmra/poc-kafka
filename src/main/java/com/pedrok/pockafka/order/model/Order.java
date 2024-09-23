package com.pedrok.pockafka.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record Order(String id, String customerId, BigDecimal amount, OrderStatusEnum status, LocalDateTime createdAt) {
    public enum OrderStatusEnum {
        CREATED,
        APPROVED,
        CANCELLED
    }
}

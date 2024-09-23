package com.pedrok.pockafka.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

@Builder
public record Order(String id,
                    String customerId,
                    BigDecimal amount,
                    OrderStatusEnum status,
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                    LocalDateTime createdAt) {
    public enum OrderStatusEnum {
        CREATED,
        APPROVED,
        CANCELLED
    }
}

package com.pedrok.pockafka.notification.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Notification(String id, String userId, String message, LocalDateTime createdAt) {
}

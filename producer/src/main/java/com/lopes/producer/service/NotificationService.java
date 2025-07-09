package com.lopes.producer.service;

import com.lopes.commons.Notification;
import com.lopes.commons.NotificationInput;
import com.lopes.commons.enums.NotificationEvent;
import com.lopes.producer.appender.NotificationAppender;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationAppender appender;

    public String send(@NotNull final NotificationInput input) {
        if (!StringUtils.hasText(input.getMessage())) {
            throw new IllegalArgumentException("Error whiling sending message");
        }

        appender.append(Notification.builder()
                .id(UUID.randomUUID())
                .event(NotificationEvent.SUBMITTED)
                .message(input.getMessage())
                .build());

        return "message sent successfully";
    }
}

package com.lopes.commons;

import com.lopes.commons.enums.NotificationEvent;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Notification {
    @NonNull
    UUID id;
    @NonNull
    NotificationEvent event;
}

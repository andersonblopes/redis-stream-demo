package com.lopes.commons.enums;

import lombok.Generated;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum NotificationEvent {
    SUBMITTED("SUBMITTED"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    // Map from event string (uppercase) to enum constant, for efficient lookup
    private static final Map<String, NotificationEvent> ALL = Arrays.stream(values())
            .collect(Collectors.toMap(ne -> ne.event, Function.identity()));

    private final String event;

    @Generated
    private NotificationEvent(final String event) {
        this.event = event;
    }

    /**
     * Parses a string into a NotificationEvent enum.
     *
     * @param input the event name, case-insensitive
     * @return the corresponding NotificationEvent enum
     * @throws IllegalArgumentException if input is null, empty, or unknown
     */
    public static NotificationEvent of(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Notification event cannot be null or empty");
        }
        return Optional.ofNullable(ALL.get(input.toUpperCase()))
                .orElseThrow(() -> new IllegalArgumentException("Unknown notification event: " + input));
    }

    @Generated
    public String getEvent() {
        return event;
    }
}


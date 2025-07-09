package com.commons;

import com.lopes.commons.enums.NotificationEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotificationEventTest {

    @Test
    void givenValidEvent_whenOf_thenReturnEnum() {
        assertEquals(NotificationEvent.SUBMITTED, NotificationEvent.of("SUBMITTED"));
        assertEquals(NotificationEvent.SUBMITTED, NotificationEvent.of("submitted"));
        assertEquals(NotificationEvent.ACCEPTED, NotificationEvent.of("ACCEPTED"));
        assertEquals(NotificationEvent.ACCEPTED, NotificationEvent.of("accepted"));
        assertEquals(NotificationEvent.REJECTED, NotificationEvent.of("REJECTED"));
        assertEquals(NotificationEvent.REJECTED, NotificationEvent.of("rejected"));
    }

    @Test
    void givenNullInput_whenOf_thenThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> NotificationEvent.of(null));
        assertEquals("Notification event cannot be null or empty", exception.getMessage());
    }

    @Test
    void givenEmptyOrBlankInput_whenOf_thenThrowIllegalArgumentException() {
        IllegalArgumentException exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> NotificationEvent.of(""));
        assertEquals("Notification event cannot be null or empty", exceptionEmpty.getMessage());

        IllegalArgumentException exceptionBlank = assertThrows(IllegalArgumentException.class, () -> NotificationEvent.of("  "));
        assertEquals("Notification event cannot be null or empty", exceptionBlank.getMessage());
    }

    @Test
    void givenUnknownInput_whenOf_thenThrowIllegalArgumentException() {
        String unknownEvent = "UNKNOWN_EVENT";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> NotificationEvent.of(unknownEvent));
        assertEquals("Unknown notification event: " + unknownEvent, exception.getMessage());
    }
}

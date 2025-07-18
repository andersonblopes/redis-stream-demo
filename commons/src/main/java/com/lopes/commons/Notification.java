package com.lopes.commons;

import com.lopes.commons.enums.NotificationEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Notification {
    @NonNull
    UUID id;
    @NonNull
    NotificationEvent event;
    @NotBlank
    String message;
}

package com.lopes.producer.resource;

import com.lopes.commons.NotificationInput;
import com.lopes.producer.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class NotificationResource {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@Valid @RequestBody NotificationInput input) {
        String messageId = notificationService.send(input);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(messageId);

    }
}


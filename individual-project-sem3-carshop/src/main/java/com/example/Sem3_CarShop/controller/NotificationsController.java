package com.example.Sem3_CarShop.controller;

import com.example.Sem3_CarShop.domain.NotificationsDomain.NotificationMessage;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@Generated
@RestController
@AllArgsConstructor
@RequestMapping("notifications")
@CrossOrigin("http://localhost:3000")
public class NotificationsController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Void> sendNotificationToUsers(@RequestBody NotificationMessage message) {
        messagingTemplate.convertAndSend("/topic/publicmessages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

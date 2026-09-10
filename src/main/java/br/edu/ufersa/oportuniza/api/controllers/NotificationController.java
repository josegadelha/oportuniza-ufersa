package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.NotificationCreate;
import br.edu.ufersa.oportuniza.api.dtos.NotificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@Validated
public class NotificationController {

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> list() {
        return null;
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponse> findById(
            @PathVariable Long notificationId
    ) {
        return null;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> create(
            @RequestBody @Valid NotificationCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            @PathVariable Long notificationId
    ) {
        return null;
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long notificationId
    ) {
        return null;
    }
}
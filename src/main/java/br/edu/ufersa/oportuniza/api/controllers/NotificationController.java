package br.edu.ufersa.oportuniza.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ufersa.oportuniza.api.dtos.NotificationCreate;
import br.edu.ufersa.oportuniza.api.dtos.NotificationResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class NotificationController {

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationResponse>> list() {
        return null;
    }

    @GetMapping("/notifications/{notificationId}")
    public ResponseEntity<NotificationResponse> findById(
            @PathVariable Long notificationId
    ) {
        return null;
    }

    @PostMapping("/notifications")
    public ResponseEntity<NotificationResponse> create(
            @RequestBody @Valid NotificationCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PatchMapping("/notifications/{notificationId}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            @PathVariable Long notificationId
    ) {
        return null;
    }

    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long notificationId
    ) {
        return null;
    }
}
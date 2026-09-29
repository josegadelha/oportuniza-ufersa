package br.edu.ufersa.oportuniza.notification;

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.notification.dto.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationApplicationService service;

    public NotificationController(NotificationApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> list(@AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.listMine(principal.getUser()));
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponse> findById(@PathVariable Long notificationId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.findById(notificationId, principal.getUser()));
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long notificationId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.markAsRead(notificationId, principal.getUser()));
    }
}

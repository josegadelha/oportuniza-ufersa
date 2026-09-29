package br.edu.ufersa.oportuniza.notification;

import br.edu.ufersa.oportuniza.notification.dto.NotificationResponse;
import br.edu.ufersa.oportuniza.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationMapper {

    public Notification toEntity(User recipient, String title, String message, NotificationType type) {
        return new Notification.Builder(recipient, title, message, type).build();
    }

    public NotificationResponse toResponse(Notification notification) {
        if (notification == null) return null;
        return new NotificationResponse(notification.getId(), notification.getRecipient().getId(),
                notification.getTitle(), notification.getMessage(), notification.getType(),
                notification.isRead(), notification.getSentAt());
    }

    public List<NotificationResponse> toResponseList(List<Notification> notifications) {
        if (notifications == null) return null;
        return notifications.stream().map(this::toResponse).toList();
    }
}

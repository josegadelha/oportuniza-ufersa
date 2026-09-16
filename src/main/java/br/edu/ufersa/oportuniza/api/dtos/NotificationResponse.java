package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDate;

import br.edu.ufersa.oportuniza.domain.entities.Notification;
import br.edu.ufersa.oportuniza.domain.entities.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O destinatário não pode ser nulo na resposta!")
        Long recipientId,

        @NotBlank(message = "O título não pode ser vazio na resposta!")
        String title,

        @NotBlank(message = "A mensagem não pode ser vazia na resposta!")
        String message,

        @NotNull(message = "O tipo não pode ser nulo na resposta!")
        NotificationType type,

        @NotNull(message = "O status de leitura não pode ser nulo na resposta!")
        Boolean isRead,

        @NotNull(message = "A data de envio não pode ser nula na resposta!")
        LocalDate sentAt
) {

    public static NotificationResponse fromEntity(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getRecipient().getId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getType(),
                notification.isRead(),
                notification.getSentAt());
    }
}
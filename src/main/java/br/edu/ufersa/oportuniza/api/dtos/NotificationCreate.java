package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationCreate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotBlank(message = "A mensagem é obrigatória!")
        String message,

        @NotNull(message = "O tipo da notificação é obrigatório!")
        NotificationType type
) {
}
package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Deliverable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DeliverableResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O id do projeto não pode ser nulo na resposta!")
        Long projectId,

        @NotBlank(message = "O título não pode ser vazio na resposta!")
        String title,

        @NotNull(message = "O prazo não pode ser nulo na resposta!")
        LocalDate deadline
) {

    public static DeliverableResponse fromEntity(Deliverable deliverable) {
        return new DeliverableResponse(
                deliverable.getId(),
                deliverable.getProject().getId(),
                deliverable.getTitle(),
                deliverable.getDeadline());
    }
}
package br.edu.ufersa.oportuniza.deliverable.dto;

import java.time.LocalDate;

import br.edu.ufersa.oportuniza.deliverable.Deliverable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
}

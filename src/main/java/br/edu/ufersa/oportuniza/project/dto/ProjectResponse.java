package br.edu.ufersa.oportuniza.project.dto;

import br.edu.ufersa.oportuniza.project.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record ProjectResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotBlank(message = "O título não pode ser vazio na resposta!")
        String title,

        @NotNull(message = "A data de início não pode ser nula na resposta!")
        LocalDate startDate,

        LocalDate endDate,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        ProjectStatus status,

        @NotNull(message = "Os orientadores não podem ser nulos na resposta!")
        List<Long> advisorIds,

        @NotNull(message = "Os participantes não podem ser nulos na resposta!")
        List<Long> memberIds
) {
}

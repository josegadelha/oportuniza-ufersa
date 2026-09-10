package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Project;
import br.edu.ufersa.oportuniza.domain.entities.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotBlank(message = "O título não pode ser vazio na resposta!")
        String title,

        @NotNull(message = "A data de início não pode ser nula na resposta!")
        LocalDate startDate,

        LocalDate endDate,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        ProjectStatus status
) {

    public static ProjectResponse fromEntity(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus());
    }
}
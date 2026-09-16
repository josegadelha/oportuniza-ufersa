package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDate;
import java.util.Set;

import br.edu.ufersa.oportuniza.domain.entities.Project;
import br.edu.ufersa.oportuniza.domain.entities.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        Set<Long> advisorIds,

        @NotNull(message = "Os participantes não podem ser nulos na resposta!")
        Set<Long> memberIds
) {

    public static ProjectResponse fromEntity(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getAdvisors().stream().map(p -> p.getId()).collect(java.util.stream.Collectors.toSet()),
                project.getMembers().stream().map(s -> s.getId()).collect(java.util.stream.Collectors.toSet()));
    }
}
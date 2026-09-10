package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.edu.ufersa.oportuniza.domain.entities.OpportunityStatus;
import br.edu.ufersa.oportuniza.domain.entities.OpportunityType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OpportunityResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O professor não pode ser nulo na resposta!")
        Long professorId,

        @NotBlank(message = "O título não pode ser vazio na resposta!")
        String title,

        @NotBlank(message = "A descrição não pode ser vazia na resposta!")
        String description,

        @NotNull(message = "O tipo não pode ser nulo na resposta!")
        OpportunityType type,

        @NotNull(message = "A quantidade de vagas não pode ser nula na resposta!")
        @Min(value = 1, message = "A quantidade de vagas deve ser maior que zero!")
        Integer positions,

        @NotNull(message = "A carga horária não pode ser nula na resposta!")
        @Min(value = 0, message = "A carga horária não pode ser negativa!")
        Integer workloadHours,

        @NotNull(message = "A remuneração não pode ser nula na resposta!")
        @DecimalMin(value = "0.0", message = "A remuneração não pode ser negativa!")
        Double remuneration,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        OpportunityStatus status,

        LocalDateTime publishedAt,

        LocalDate applicationDeadline,

        List<String> requirements
) {
}

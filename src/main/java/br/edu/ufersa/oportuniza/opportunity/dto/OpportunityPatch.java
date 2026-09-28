package br.edu.ufersa.oportuniza.opportunity.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OpportunityPatch(

        @Size(min = 1, message = "O título não pode ser vazio!")
        String title,

        @Size(min = 1, message = "A descrição não pode ser vazia!")
        String description,

        OpportunityType type,

        @Min(value = 1, message = "A quantidade de vagas deve ser maior que zero!")
        Integer positions,

        @Min(value = 0, message = "A carga horária não pode ser negativa!")
        Integer workloadHours,

        @DecimalMin(value = "0.0", message = "A remuneração não pode ser negativa!")
        Double remuneration,

        OpportunityStatus status,

        LocalDateTime publishedAt,

        LocalDate applicationDeadline,

        List<String> requirements
) {
}

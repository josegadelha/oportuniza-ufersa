package br.edu.ufersa.oportuniza.opportunity.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OpportunityUpdate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotBlank(message = "A descrição é obrigatória!")
        String description,

        @NotNull(message = "O tipo da oportunidade é obrigatório!")
        OpportunityType type,

        @NotNull(message = "A quantidade de vagas é obrigatória!")
        @Min(value = 1, message = "A quantidade de vagas deve ser maior que zero!")
        Integer positions,

        @NotNull(message = "A carga horária é obrigatória!")
        @Min(value = 0, message = "A carga horária não pode ser negativa!")
        Integer workloadHours,

        @NotNull(message = "A remuneração é obrigatória!")
        @DecimalMin(value = "0.0", message = "A remuneração não pode ser negativa!")
        Double remuneration,

        @NotNull(message = "O status é obrigatório!")
        OpportunityStatus status,

        @NotNull(message = "A data de publicação é obrigatória!")
        LocalDateTime publishedAt,

        LocalDate applicationDeadline,

        List<String> requirements
) {
}

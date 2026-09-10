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

public record OpportunityCreate(

        @NotNull(message = "O professor é obrigatório!")
        Long professorId,

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

        OpportunityStatus status,

        LocalDateTime publishedAt,

        LocalDate applicationDeadline,

        List<String> requirements
) {
}

package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.edu.ufersa.oportuniza.domain.entities.OpportunityStatus;
import br.edu.ufersa.oportuniza.domain.entities.OpportunityType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

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

package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.domain.entities.CandidacyStatus;
import jakarta.validation.constraints.NotNull;

public record CandidacyUpdate(

        @NotNull(message = "O estudante é obrigatório!")
        Long studentId,

        @NotNull(message = "A oportunidade é obrigatória!")
        Long opportunityId,

        @NotNull(message = "A data da candidatura é obrigatória!")
        LocalDateTime appliedAt,

        @NotNull(message = "O status da candidatura é obrigatório!")
        CandidacyStatus status
) {
}
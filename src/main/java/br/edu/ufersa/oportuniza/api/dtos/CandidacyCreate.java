package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.domain.entities.CandidacyStatus;
import jakarta.validation.constraints.NotNull;

public record CandidacyCreate(

        @NotNull(message = "O estudante é obrigatório!")
        Long studentId,

        @NotNull(message = "A oportunidade é obrigatória!")
        Long opportunityId,

        LocalDateTime appliedAt,

        CandidacyStatus status
) {
}

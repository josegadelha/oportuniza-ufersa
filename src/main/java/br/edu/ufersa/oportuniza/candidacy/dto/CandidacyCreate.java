package br.edu.ufersa.oportuniza.candidacy.dto;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.candidacy.CandidacyStatus;
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

package br.edu.ufersa.oportuniza.candidacy.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CandidacyCreate(

        @NotNull(message = "O estudante é obrigatório!")
        Long studentId,

        @NotNull(message = "A oportunidade é obrigatória!")
        Long opportunityId,

        LocalDateTime appliedAt,

        CandidacyStatus status
) {
}

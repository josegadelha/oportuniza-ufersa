package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.domain.entities.CandidacyStatus;

public record CandidacyPatch(

        Long studentId,

        Long opportunityId,

        LocalDateTime appliedAt,

        CandidacyStatus status
) {
}
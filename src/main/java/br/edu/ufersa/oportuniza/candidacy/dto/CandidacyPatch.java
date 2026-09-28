package br.edu.ufersa.oportuniza.candidacy.dto;

import java.time.LocalDateTime;

public record CandidacyPatch(

        Long studentId,

        Long opportunityId,

        LocalDateTime appliedAt,

        CandidacyStatus status
) {
}
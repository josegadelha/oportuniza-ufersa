package br.edu.ufersa.oportuniza.candidacy.dto;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.candidacy.CandidacyStatus;

public record CandidacyPatch(

        Long studentId,

        Long opportunityId,

        LocalDateTime appliedAt,

        CandidacyStatus status
) {
}
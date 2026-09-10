package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.domain.entities.InterestStatus;

public record ProposalInterestPatch(

        Long projectProposalId,

        Long userId,

        LocalDateTime createdAt,

        InterestStatus status
) {
}

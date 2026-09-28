package br.edu.ufersa.oportuniza.proposalinterest.dto;

import java.time.LocalDateTime;

public record ProposalInterestPatch(

        Long projectProposalId,

        Long userId,

        LocalDateTime createdAt,

        InterestStatus status
) {
}

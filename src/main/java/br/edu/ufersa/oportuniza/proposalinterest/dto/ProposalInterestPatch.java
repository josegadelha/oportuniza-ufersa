package br.edu.ufersa.oportuniza.proposalinterest.dto;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.proposalinterest.InterestStatus;

public record ProposalInterestPatch(

        Long projectProposalId,

        Long userId,

        LocalDateTime createdAt,

        InterestStatus status
) {
}

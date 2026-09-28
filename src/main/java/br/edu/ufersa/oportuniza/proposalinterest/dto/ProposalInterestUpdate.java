package br.edu.ufersa.oportuniza.proposalinterest.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProposalInterestUpdate(

        @NotNull(message = "A proposta de projeto é obrigatória!")
        Long projectProposalId,

        @NotNull(message = "O usuário interessado é obrigatório!")
        Long userId,

        @NotNull(message = "A data de criação é obrigatória!")
        LocalDateTime createdAt,

        @NotNull(message = "O status do interesse é obrigatório!")
        InterestStatus status
) {
}

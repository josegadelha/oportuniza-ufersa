package br.edu.ufersa.oportuniza.proposalinterest.dto;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.proposalinterest.InterestStatus;
import jakarta.validation.constraints.NotNull;

public record ProposalInterestResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "A proposta de projeto não pode ser nula na resposta!")
        Long projectProposalId,

        @NotNull(message = "O usuário interessado não pode ser nulo na resposta!")
        Long userId,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        InterestStatus status,

        @NotNull(message = "A data de criação não pode ser nula na resposta!")
        LocalDateTime createdAt
) {
}

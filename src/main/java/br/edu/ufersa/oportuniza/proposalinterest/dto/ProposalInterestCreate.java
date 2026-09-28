package br.edu.ufersa.oportuniza.proposalinterest.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProposalInterestCreate(

        @NotNull(message = "O usuário interessado é obrigatório!")
        Long userId,

        InterestStatus status,

        LocalDateTime createdAt
) {
}

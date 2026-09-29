package br.edu.ufersa.oportuniza.proposalinterest.dto;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.proposalinterest.InterestStatus;
import jakarta.validation.constraints.NotNull;

public record ProposalInterestCreate(

        @NotNull(message = "O usuário interessado é obrigatório!")
        Long userId,

        InterestStatus status,

        LocalDateTime createdAt
) {
}

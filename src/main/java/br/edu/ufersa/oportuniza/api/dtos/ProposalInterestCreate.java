package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.domain.entities.InterestStatus;
import jakarta.validation.constraints.NotNull;

public record ProposalInterestCreate(

        @NotNull(message = "A proposta de projeto é obrigatória!")
        Long projectProposalId,

        @NotNull(message = "O usuário interessado é obrigatório!")
        Long userId,

        InterestStatus status,

        LocalDateTime createdAt
) {
}

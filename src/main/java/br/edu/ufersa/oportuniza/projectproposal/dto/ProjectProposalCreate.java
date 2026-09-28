package br.edu.ufersa.oportuniza.projectproposal.dto;

import br.edu.ufersa.oportuniza.proposal.ProposalStatus;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectProposalCreate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotBlank(message = "A descrição é obrigatória!")
        String description,

        ProposalStatus status,

        LocalDateTime publishedAt,

        List<String> desiredSkills
) {
}

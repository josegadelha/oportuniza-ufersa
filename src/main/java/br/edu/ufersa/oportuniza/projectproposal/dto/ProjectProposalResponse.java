package br.edu.ufersa.oportuniza.projectproposal.dto;

import br.edu.ufersa.oportuniza.proposal.ProposalStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectProposalResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O estudante não pode ser nulo na resposta!")
        Long studentId,

        @NotBlank(message = "O título não pode ser vazio na resposta!")
        String title,

        @NotBlank(message = "A descrição não pode ser vazia na resposta!")
        String description,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        ProposalStatus status,

        @NotNull(message = "A data de publicação não pode ser nula na resposta!")
        LocalDateTime publishedAt,

        List<String> desiredSkills
) {
}

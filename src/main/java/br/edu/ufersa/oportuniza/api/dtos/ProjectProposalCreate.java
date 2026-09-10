package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ufersa.oportuniza.domain.entities.ProposalStatus;
import jakarta.validation.constraints.NotBlank;

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

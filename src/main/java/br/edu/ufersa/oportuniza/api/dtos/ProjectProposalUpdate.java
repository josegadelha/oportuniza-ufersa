package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ufersa.oportuniza.domain.entities.ProposalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectProposalUpdate(

        @NotNull(message = "O estudante é obrigatório!")
        Long studentId,

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotBlank(message = "A descrição é obrigatória!")
        String description,

        @NotNull(message = "O status da proposta é obrigatório!")
        ProposalStatus status,

        @NotNull(message = "A data de publicação é obrigatória!")
        LocalDateTime publishedAt,

        List<String> desiredSkills
) {
}

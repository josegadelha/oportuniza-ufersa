package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ufersa.oportuniza.domain.entities.ProposalStatus;
import jakarta.validation.constraints.Size;

public record ProjectProposalPatch(

        Long studentId,

        @Size(min = 1, message = "O título não pode ser vazio!")
        String title,

        @Size(min = 1, message = "A descrição não pode ser vazia!")
        String description,

        ProposalStatus status,

        LocalDateTime publishedAt,

        List<String> desiredSkills
) {
}

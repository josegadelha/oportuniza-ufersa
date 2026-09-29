package br.edu.ufersa.oportuniza.candidacy.dto;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.candidacy.CandidacyStatus;
import jakarta.validation.constraints.NotNull;

public record CandidacyResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O estudante não pode ser nulo na resposta!")
        Long studentId,

        @NotNull(message = "A oportunidade não pode ser nula na resposta!")
        Long opportunityId,

        @NotNull(message = "A data da candidatura não pode ser nula na resposta!")
        LocalDateTime appliedAt,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        CandidacyStatus status
) {
}

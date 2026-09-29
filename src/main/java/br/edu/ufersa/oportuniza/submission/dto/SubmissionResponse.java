package br.edu.ufersa.oportuniza.submission.dto;

import br.edu.ufersa.oportuniza.submission.SubmissionStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SubmissionResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O id do entregável não pode ser nulo na resposta!")
        Long deliverableId,

        @NotNull(message = "O estudante não pode ser nulo na resposta!")
        Long studentId,

        String filePath,

        LocalDateTime submittedAt,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        SubmissionStatus status
) {
}

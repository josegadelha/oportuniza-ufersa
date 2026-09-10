package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Submission;
import br.edu.ufersa.oportuniza.domain.entities.SubmissionStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SubmissionResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotNull(message = "O id do entregável não pode ser nulo na resposta!")
        Long deliverableId,

        String filePath,

        LocalDateTime submittedAt,

        @NotNull(message = "O status não pode ser nulo na resposta!")
        SubmissionStatus status
) {

    public static SubmissionResponse fromEntity(Submission submission) {
        return new SubmissionResponse(
                submission.getId(),
                submission.getDeliverable().getId(),
                submission.getFilePath(),
                submission.getSubmittedAt(),
                submission.getStatus());
    }
}
package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Submission {

    private final Long id;
    private final Deliverable deliverable;
    private String filePath;
    private LocalDateTime submittedAt;
    private SubmissionStatus status;

    private Submission(Builder builder) {
        this.id = builder.id;
        this.deliverable = builder.deliverable;
        this.filePath = builder.filePath;
        this.submittedAt = builder.submittedAt;
        this.status = builder.status;
    }

    public void submit(String filePath) {
        validateTransition(SubmissionStatus.PENDING);
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("O caminho do arquivo é obrigatório para o envio.");
        }
        this.filePath = filePath;
        this.submittedAt = LocalDateTime.now();
        this.status = SubmissionStatus.PENDING;
    }

    public void approve() {
        validateTransition(SubmissionStatus.APPROVED);
        this.status = SubmissionStatus.APPROVED;
    }

    public void reject() {
        validateTransition(SubmissionStatus.REJECTED);
        this.status = SubmissionStatus.REJECTED;
    }

    private void validateTransition(SubmissionStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new IllegalStateException(
                    String.format("Transição inválida: submissão está em '%s' e não pode ir para '%s'.",
                            this.status, nextStatus)
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Deliverable getDeliverable() {
        return deliverable;
    }

    public String getFilePath() {
        return filePath;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public static class Builder {

        // Obrigatório
        private final Deliverable deliverable;

        // Opcionais com valores padrão explícitos
        private Long id;
        private String filePath;
        private LocalDateTime submittedAt;
        private SubmissionStatus status = SubmissionStatus.WAITING;

        public Builder(Deliverable deliverable) {
            this.deliverable = Objects.requireNonNull(deliverable, "O entregável é obrigatório!");
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder withSubmittedAt(LocalDateTime submittedAt) {
            this.submittedAt = submittedAt;
            return this;
        }

        public Builder withStatus(SubmissionStatus status) {
            this.status = status;
            return this;
        }

        public Submission build() {
            validateInvariants();
            return new Submission(this);
        }

        private void validateInvariants() {
            if (status != SubmissionStatus.WAITING && (filePath == null || filePath.isBlank())) {
                throw new IllegalArgumentException("O caminho do arquivo é obrigatório quando a submissão não está aguardando envio.");
            }
        }
    }
}
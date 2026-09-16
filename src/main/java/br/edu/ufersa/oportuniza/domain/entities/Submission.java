package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deliverable_id", nullable = false)
    private Deliverable deliverable;

    @Column(name = "file_path", length = 255)
    private String filePath;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubmissionStatus status;

    protected Submission() {
    }

    private Submission(Builder builder) {
        this.id = builder.id;
        this.deliverable = builder.deliverable;
        this.filePath = builder.filePath;
        this.submittedAt = builder.submittedAt;
        this.status = builder.status;
    }

    public void submit(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("O caminho do arquivo é obrigatório para o envio.");
        }
        validateTransition(SubmissionStatus.PENDING);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Submission other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static class Builder {

        //Obrigatório
        private final Deliverable deliverable;

        //Opcionais
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
            if (status == SubmissionStatus.WAITING) {
                return;
            }
            if (filePath == null || filePath.isBlank()) {
                throw new IllegalArgumentException("O caminho do arquivo é obrigatório quando a submissão não está aguardando envio.");
            }
            if (submittedAt == null) {
                throw new IllegalArgumentException("A data de envio é obrigatória quando a submissão não está aguardando envio.");
            }
        }
    }
}
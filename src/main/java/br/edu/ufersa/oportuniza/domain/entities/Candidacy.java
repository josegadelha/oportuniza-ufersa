package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Candidacy {
    private final Long id;

    private final Student student;

    private final Opportunity opportunity;

    private final LocalDateTime appliedAt;

    private CandidacyStatus status;

    private Candidacy(Builder builder) {
        this.id = builder.id;
        this.student = builder.student;
        this.opportunity = builder.opportunity;
        this.appliedAt = builder.appliedAt;
        this.status = builder.status;
    }

    public void updateStatus(CandidacyStatus status) {
        this.status = Objects.requireNonNull(status, "O status da candidatura é obrigatório!");
    }

    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public Opportunity getOpportunity() { return opportunity; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public CandidacyStatus getStatus() { return status; }

    public static class Builder {
        private Long id;
        private final Student student;
        private final Opportunity opportunity;
        private LocalDateTime appliedAt = LocalDateTime.now();
        private CandidacyStatus status = CandidacyStatus.IN_SELECTION;

        public Builder(Student student, Opportunity opportunity) {
            this.student = Objects.requireNonNull(student, "O estudante é obrigatório!");
            this.opportunity = Objects.requireNonNull(opportunity, "A oportunidade é obrigatória!");
        }

        public Builder withId(Long id) { this.id = id; return this; }
        public Builder withAppliedAt(LocalDateTime appliedAt) { this.appliedAt = Objects.requireNonNull(appliedAt, "A data da candidatura é obrigatória!"); return this; }
        public Builder withStatus(CandidacyStatus status) { this.status = Objects.requireNonNull(status, "O status da candidatura é obrigatório!"); return this; }
        public Candidacy build() { return new Candidacy(this); }
    }
}

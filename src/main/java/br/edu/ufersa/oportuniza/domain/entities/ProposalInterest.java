package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProposalInterest {
    private final Long id;

    private final ProjectProposal projectProposal;

    private final User user;

    private InterestStatus status;

    private final LocalDateTime createdAt;

    private ProposalInterest(Builder builder) {
        this.id = builder.id;
        this.projectProposal = builder.projectProposal;
        this.user = builder.user;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    public void accept() { this.status = InterestStatus.ACCEPTED; }
    public void reject() { this.status = InterestStatus.REJECTED; }
    public Long getId() { return id; }
    public ProjectProposal getProjectProposal() { return projectProposal; }
    public User getUser() { return user; }
    public InterestStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public static class Builder {
        private Long id;
        private final ProjectProposal projectProposal;
        private final User user;
        private InterestStatus status = InterestStatus.PENDING;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder(ProjectProposal projectProposal, User user) {
            this.projectProposal = Objects.requireNonNull(projectProposal, "A proposta de projeto é obrigatória!");
            this.user = Objects.requireNonNull(user, "O usuário interessado é obrigatório!");
        }

        public Builder withId(Long id) { this.id = id; return this; }
        public Builder withStatus(InterestStatus status) { this.status = Objects.requireNonNull(status, "O status do interesse é obrigatório!"); return this; }
        public Builder withCreatedAt(LocalDateTime createdAt) { this.createdAt = Objects.requireNonNull(createdAt, "A data de criação é obrigatória!"); return this; }
        public ProposalInterest build() { return new ProposalInterest(this); }
    }
}

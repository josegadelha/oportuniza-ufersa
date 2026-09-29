package br.edu.ufersa.oportuniza.proposalinterest;

import java.time.LocalDateTime;
import java.util.Objects;

import br.edu.ufersa.oportuniza.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposal_interests")
class ProposalInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_proposal_id", nullable = false)
    private Long projectProposalId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InterestStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected ProposalInterest() {
    }

    private ProposalInterest(Builder builder) {
        this.id = builder.id;
        this.projectProposalId = builder.projectProposalId;
        this.user = builder.user;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    public void accept() {
        this.status = InterestStatus.ACCEPTED;
    }

    public void reject() {
        this.status = InterestStatus.REJECTED;
    }

    public Long getId() {
        return id;
    }

    public Long getProjectProposalId() {
        return projectProposalId;
    }

    public User getUser() {
        return user;
    }

    public InterestStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static class Builder {

        private Long id;

        private final Long projectProposalId;
        private final User user;

        private InterestStatus status = InterestStatus.PENDING;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder(Long projectProposalId, User user) {
            this.projectProposalId = Objects.requireNonNull(
                projectProposalId,
                "A proposta de projeto é obrigatória!"
            );

            this.user = Objects.requireNonNull(
                user,
                "O usuário interessado é obrigatório!"
            );
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(InterestStatus status) {
            this.status = Objects.requireNonNull(
                status,
                "O status do interesse é obrigatório!"
            );

            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = Objects.requireNonNull(
                createdAt,
                "A data de criação é obrigatória!"
            );

            return this;
        }

        public ProposalInterest build() {
            return new ProposalInterest(this);
        }
    }
}
package br.edu.ufersa.oportuniza.candidacy;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.shared.exception.BusinessValidation;
import br.edu.ufersa.oportuniza.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "candidacies",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"student_id", "opportunity_id"}
        )
)
class Candidacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "opportunity_id", nullable = false)
    private Long opportunityId;

    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CandidacyStatus status;

    protected Candidacy() {
    }

    private Candidacy(Builder builder) {
        this.id = builder.id;
        this.student = builder.student;
        this.opportunityId = builder.opportunityId;
        this.appliedAt = builder.appliedAt;
        this.status = builder.status;
    }

    public void updateStatus(CandidacyStatus nextStatus) {
        CandidacyStatus status = BusinessValidation.requireNonNull(
                nextStatus,
                "O status da candidatura é obrigatório!"
        );

        if (!isValidTransition(this.status, status)) {
            throw new IllegalArgumentException(
                    "Transição de status inválida para a candidatura."
            );
        }

        this.status = status;
    }

    private static boolean isValidTransition(
            CandidacyStatus current,
            CandidacyStatus next
    ) {
        if (current == null) {
            return true;
        }

        return switch (current) {
            case IN_SELECTION ->
                    next == CandidacyStatus.HISTORY_REVIEW
                    || next == CandidacyStatus.REJECTED
                    || next == CandidacyStatus.WITHDRAWN;

            case HISTORY_REVIEW ->
                    next == CandidacyStatus.INTERVIEW
                    || next == CandidacyStatus.REJECTED
                    || next == CandidacyStatus.WITHDRAWN;

            case INTERVIEW ->
                    next == CandidacyStatus.FINAL_REVIEW
                    || next == CandidacyStatus.REJECTED
                    || next == CandidacyStatus.WITHDRAWN;

            case FINAL_REVIEW ->
                    next == CandidacyStatus.APPROVED
                    || next == CandidacyStatus.REJECTED
                    || next == CandidacyStatus.WITHDRAWN;

            case APPROVED, REJECTED, WITHDRAWN -> false;
        };
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public CandidacyStatus getStatus() {
        return status;
    }

    static class Builder {

        private Long id;

        private final Student student;
        private final Long opportunityId;

        private LocalDateTime appliedAt = LocalDateTime.now();
        private CandidacyStatus status = CandidacyStatus.IN_SELECTION;

        Builder(Student student, Long opportunityId) {
            this.student = BusinessValidation.requireNonNull(
                    student,
                    "O estudante é obrigatório!"
            );

            this.opportunityId = BusinessValidation.requireNonNull(
                    opportunityId,
                    "A oportunidade é obrigatória!"
            );
        }

        Builder withId(Long id) {
            this.id = id;
            return this;
        }

        Builder withAppliedAt(LocalDateTime appliedAt) {
            this.appliedAt = BusinessValidation.requireNonNull(
                    appliedAt,
                    "A data da candidatura é obrigatória!"
            );

            return this;
        }

        Builder withStatus(CandidacyStatus status) {
            this.status = BusinessValidation.requireNonNull(
                    status,
                    "O status da candidatura é obrigatório!"
            );

            return this;
        }

        Candidacy build() {
            return new Candidacy(this);
        }
    }
}
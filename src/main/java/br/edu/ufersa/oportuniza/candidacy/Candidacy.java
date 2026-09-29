package br.edu.ufersa.oportuniza.candidacy;

import br.edu.ufersa.oportuniza.shared.exception.BusinessValidation;

import java.time.LocalDateTime;

import br.edu.ufersa.oportuniza.opportunity.Opportunity;
import br.edu.ufersa.oportuniza.student.Student;
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
@Table(name = "candidacies")
public class Candidacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;

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
        this.opportunity = builder.opportunity;
        this.appliedAt = builder.appliedAt;
        this.status = builder.status;
    }

    public void updateStatus(CandidacyStatus status) {
        this.status = BusinessValidation.requireNonNull(
            status,
            "O status da candidatura é obrigatório!"
        );
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public CandidacyStatus getStatus() {
        return status;
    }

    public static class Builder {

        private Long id;

        private final Student student;
        private final Opportunity opportunity;

        private LocalDateTime appliedAt = LocalDateTime.now();
        private CandidacyStatus status = CandidacyStatus.IN_SELECTION;

        public Builder(Student student, Opportunity opportunity) {
            this.student = BusinessValidation.requireNonNull(
                student,
                "O estudante é obrigatório!"
            );

            this.opportunity = BusinessValidation.requireNonNull(
                opportunity,
                "A oportunidade é obrigatória!"
            );
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withAppliedAt(LocalDateTime appliedAt) {
            this.appliedAt = BusinessValidation.requireNonNull(
                appliedAt,
                "A data da candidatura é obrigatória!"
            );

            return this;
        }

        public Builder withStatus(CandidacyStatus status) {
            this.status = BusinessValidation.requireNonNull(
                status,
                "O status da candidatura é obrigatório!"
            );

            return this;
        }

        public Candidacy build() {
            return new Candidacy(this);
        }
    }
}
package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

public class ProjectProposal extends Proposal {

    @Column(name = "student_id", nullable = false)
    private final Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProposalStatus status;

    @ElementCollection 
    @CollectionTable(name = "project_proposal_desired_skills", joinColumns = @JoinColumn(name = "project_proposal_id"))
    @Column(name = "desired_skill", nullable = false)
    private List<String> desiredSkills;

    private ProjectProposal(Builder builder) {
        super(builder.id, builder.title, builder.description, builder.publishedAt);
        this.student = builder.student;
        this.status = builder.status;
        this.desiredSkills = new ArrayList<>(builder.desiredSkills);
    }

    public void updateStatus(ProposalStatus status) {
        this.status = Objects.requireNonNull(status, "O status da proposta é obrigatório!");
    }

    public Student getStudent() { return student; }
    public ProposalStatus getStatus() { return status; }
    @Override
    public LocalDateTime getPublishedAt() { return super.getPublishedAt(); }
    public List<String> getDesiredSkills() { return List.copyOf(desiredSkills); }

    public static class Builder {
        private Long id;
        private final Student student;
        private final String title;
        private final String description;
        private ProposalStatus status = ProposalStatus.OPEN;
        private LocalDateTime publishedAt = LocalDateTime.now();
        private List<String> desiredSkills = new ArrayList<>();

        public Builder(Student student, String title, String description) {
            this.student = Objects.requireNonNull(student, "O estudante é obrigatório!");
            this.title = title;
            this.description = description;
        }

        public Builder withId(Long id) { this.id = id; return this; }
        public Builder withStatus(ProposalStatus status) { this.status = Objects.requireNonNull(status, "O status da proposta é obrigatório!"); return this; }
        public Builder withPublishedAt(LocalDateTime publishedAt) { this.publishedAt = Objects.requireNonNull(publishedAt, "A data de publicação é obrigatória!"); return this; }
        public Builder withDesiredSkills(List<String> desiredSkills) { this.desiredSkills = desiredSkills == null ? new ArrayList<>() : new ArrayList<>(desiredSkills); return this; }
        public ProjectProposal build() {
            Objects.requireNonNull(publishedAt, "A data de publicação é obrigatória!");
            return new ProjectProposal(this);
        }
    }
}

package br.edu.ufersa.oportuniza.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "deliverables")
public class Deliverable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "deadline")
    private LocalDate deadline;

    protected Deliverable() {
    }

    private Deliverable(Builder builder) {
        this.id = builder.id;
        this.project = builder.project;
        this.title = builder.title;
        this.deadline = builder.deadline;
    }

    public void renameTitle(String newTitle) {
        ensureProjectIsActive();
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.title = newTitle;
    }

    public void postponeDeadline(LocalDate newDeadline) {
        ensureProjectIsActive();
        Objects.requireNonNull(newDeadline, "O novo prazo é obrigatório.");
        if (this.deadline != null && newDeadline.isBefore(this.deadline)) {
            throw new IllegalArgumentException("O novo prazo não pode antecipar o prazo já vigente.");
        }
        this.deadline = newDeadline;
    }

    private void ensureProjectIsActive() {
        if (project.getStatus() != ProjectStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Não é permitido alterar entregáveis de projetos que não estão ativos.");
        }
    }

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deliverable other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static class Builder {

        private final Project project;
        private final String title;

        private Long id;
        private LocalDate deadline;

        public Builder(Project project, String title) {
            this.project = Objects.requireNonNull(project, "O projeto é obrigatório!");
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("O título é obrigatório!");
            }
            this.title = title;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withDeadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Deliverable build() {
            validateInvariants();
            return new Deliverable(this);
        }

        private void validateInvariants() {
            if (deadline != null && deadline.isBefore(project.getStartDate())) {
                throw new IllegalArgumentException("O prazo do entregável não pode ser anterior ao início do projeto.");
            }
        }
    }
}
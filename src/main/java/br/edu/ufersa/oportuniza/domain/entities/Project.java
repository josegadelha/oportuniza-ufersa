package br.edu.ufersa.oportuniza.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProjectStatus status;

    protected Project() {
    }

    private Project(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;
    }

    public void complete() {
        validateTransition(ProjectStatus.COMPLETED);
        this.status = ProjectStatus.COMPLETED;
        this.endDate = LocalDate.now();
    }

    public void cancel() {
        validateTransition(ProjectStatus.CANCELLED);
        this.status = ProjectStatus.CANCELLED;
    }

    public void renameTitle(String newTitle) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new IllegalStateException("Não é permitido renomear projetos que não estão ativos.");
        }
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.title = newTitle;
    }

    public void extendEndDate(LocalDate newEndDate) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new IllegalStateException("Não é possível alterar a data de encerramento de projetos que não estão ativos.");
        }
        Objects.requireNonNull(newEndDate, "A nova data de encerramento é obrigatória.");
        if (newEndDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("A data de encerramento não pode ser anterior à data de início.");
        }
        if (this.endDate != null && newEndDate.isBefore(this.endDate)) {
            throw new IllegalArgumentException("A nova data de encerramento não pode antecipar o prazo já vigente.");
        }
        this.endDate = newEndDate;
    }

    private void validateTransition(ProjectStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new IllegalStateException(
                    String.format("Transição inválida: projeto está em '%s' e não pode ir para '%s'.",
                            this.status, nextStatus)
            );
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static class Builder {

        private final String title;
        private final LocalDate startDate;
        private Long id;
        private LocalDate endDate;
        private ProjectStatus status = ProjectStatus.ACTIVE;

        public Builder(String title, LocalDate startDate) {
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("O título é obrigatório!");
            }
            this.title = title;
            this.startDate = Objects.requireNonNull(startDate, "A data de início é obrigatória!");
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withStatus(ProjectStatus status) {
            this.status = status;
            return this;
        }

        public Project build() {
            validateInvariants();
            return new Project(this);
        }

        private void validateInvariants() {
            if (endDate != null && endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("A data de encerramento não pode ser anterior à data de início.");
            }
        }
    }
}
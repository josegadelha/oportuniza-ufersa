package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Deliverable {

    private final Long id;
    private final Project project;
    private String title;
    private LocalDate deadline;

    private Deliverable(Builder builder) {
        this.id = builder.id;
        this.project = builder.project;
        this.title = builder.title;
        this.deadline = builder.deadline;
    }

    public void renameTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.title = newTitle;
    }

    public void postponeDeadline(LocalDate newDeadline) {
        Objects.requireNonNull(newDeadline, "O novo prazo é obrigatório.");
        if (newDeadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O novo prazo não pode ser anterior à data de hoje.");
        }
        if (this.deadline != null && newDeadline.isBefore(this.deadline)) {
            throw new IllegalArgumentException("O novo prazo não pode antecipar o prazo já vigente.");
        }
        this.deadline = newDeadline;
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

    public static class Builder {

        // Obrigatórios
        private final Project project;
        private final String title;

        // Opcionais com valores padrão explícitos
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
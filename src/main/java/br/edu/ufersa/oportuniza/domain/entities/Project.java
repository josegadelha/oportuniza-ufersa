package br.edu.ufersa.oportuniza.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_advisors",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private Set<Professor> advisors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_students",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> members = new HashSet<>();

    protected Project() {
    }

    private Project(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;
        this.advisors = builder.advisors;
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

    public void addAdvisor(Professor professor) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new IllegalStateException("Não é possível adicionar orientadores a projetos que não estão ativos.");
        }
        Objects.requireNonNull(professor, "O professor é obrigatório.");
        this.advisors.add(professor);
    }

    public void removeAdvisor(Professor professor) {
        Objects.requireNonNull(professor, "O professor é obrigatório.");
        if (this.advisors.size() <= 1 && this.advisors.contains(professor)) {
            throw new IllegalStateException("O projeto deve manter pelo menos um professor orientador.");
        }
        this.advisors.remove(professor);
    }

    public void addMember(Student student) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new IllegalStateException("Não é possível adicionar participantes a projetos que não estão ativos.");
        }
        Objects.requireNonNull(student, "O aluno é obrigatório.");
        this.members.add(student);
    }

    public void removeMember(Student student) {
        Objects.requireNonNull(student, "O aluno é obrigatório.");
        this.members.remove(student);
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

    public Set<Professor> getAdvisors() {
        return Collections.unmodifiableSet(advisors);
    }

    public Set<Student> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public boolean hasAdvisor(Long professorId) {
        if (professorId == null) {
            return false;
        }
        return advisors.stream().anyMatch(p -> professorId.equals(p.getId()));
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
        private final Set<Professor> advisors;
        private Long id;
        private LocalDate endDate;
        private ProjectStatus status = ProjectStatus.ACTIVE;

        public Builder(String title, LocalDate startDate, Set<Professor> advisors) {
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("O título é obrigatório!");
            }
            this.title = title;
            this.startDate = Objects.requireNonNull(startDate, "A data de início é obrigatória!");
            Objects.requireNonNull(advisors, "É obrigatório informar pelo menos um professor orientador!");
            if (advisors.isEmpty()) {
                throw new IllegalArgumentException("O projeto deve ter pelo menos um professor orientador.");
            }
            advisors.forEach(Objects::requireNonNull);
            this.advisors = new HashSet<>(advisors);
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
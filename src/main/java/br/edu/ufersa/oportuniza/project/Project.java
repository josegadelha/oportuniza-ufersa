package br.edu.ufersa.oportuniza.project;

import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;
import br.edu.ufersa.oportuniza.shared.exception.BusinessValidation;

import br.edu.ufersa.oportuniza.professor.Professor;
import br.edu.ufersa.oportuniza.shared.exception.ProjectRuleViolationException;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "projects",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"title", "start_date"}
    )
)
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
    private List<Professor> advisors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_students",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> members = new ArrayList<>();

    protected Project() {
    }

    private Project(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;
        this.advisors = new ArrayList<>(builder.advisors);
        this.members = new ArrayList<>(builder.members);
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
            throw new ProjectRuleViolationException("Não é permitido renomear projetos que não estão ativos.");
        }
        if (newTitle == null || newTitle.isBlank()) {
            throw new InvalidBusinessDataException("O título não pode ser vazio.");
        }
        this.title = newTitle;
    }

    public void extendEndDate(LocalDate newEndDate) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new ProjectRuleViolationException("Não é possível alterar a data de encerramento de projetos que não estão ativos.");
        }
        BusinessValidation.requireNonNull(newEndDate, "A nova data de encerramento é obrigatória.");
        if (newEndDate.isBefore(LocalDate.now())) {
            throw new InvalidBusinessDataException("A data de encerramento não pode estar no passado.");
        }
        if (newEndDate.isBefore(this.startDate)) {
            throw new InvalidBusinessDataException("A data de encerramento não pode ser anterior à data de início.");
        }
        if (this.endDate != null && newEndDate.isBefore(this.endDate)) {
            throw new ProjectRuleViolationException("A nova data de encerramento não pode antecipar o prazo já vigente.");
        }
        this.endDate = newEndDate;
    }

    public void addAdvisor(Professor professor) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new ProjectRuleViolationException("Não é possível adicionar orientadores a projetos que não estão ativos.");
        }
        addUnique(this.advisors, professor, "O professor é obrigatório.");
    }

    public void removeAdvisor(Professor professor) {
        BusinessValidation.requireNonNull(professor, "O professor é obrigatório.");
        boolean isAdvisor = this.advisors.stream().anyMatch(advisor -> sameUser(advisor, professor));
        if (this.advisors.size() <= 1 && isAdvisor) {
            throw new ProjectRuleViolationException("O projeto deve manter pelo menos um professor orientador.");
        }
        this.advisors.removeIf(advisor -> sameUser(advisor, professor));
    }

    public void addMember(Student student) {
        if (this.status != ProjectStatus.ACTIVE) {
            throw new ProjectRuleViolationException("Não é possível adicionar participantes a projetos que não estão ativos.");
        }
        addUnique(this.members, student, "O aluno é obrigatório.");
    }

    public void removeMember(Student student) {
        BusinessValidation.requireNonNull(student, "O aluno é obrigatório.");
        this.members.removeIf(member -> sameUser(member, student));
    }

    private static <T extends User> void addUnique(List<T> users, T user, String message) {
        BusinessValidation.requireNonNull(user, message);
        if (users.stream().noneMatch(existing -> sameUser(existing, user))) {
            users.add(user);
        }
    }

    private static boolean sameUser(User first, User second) {
        return first == second || first.getId() != null && first.getId().equals(second.getId());
    }

    private void validateTransition(ProjectStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new ProjectRuleViolationException(
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

    public List<Professor> getAdvisors() {
        return List.copyOf(advisors);
    }

    public List<Student> getMembers() {
        return List.copyOf(members);
    }

    public boolean hasAdvisor(Long professorId) {
        if (professorId == null) {
            return false;
        }
        return advisors.stream().anyMatch(p -> professorId.equals(p.getId()));
    }

    public boolean hasMember(Long studentId) {
        return studentId != null && members.stream().anyMatch(s -> studentId.equals(s.getId()));
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
        private final List<Professor> advisors;
        private List<Student> members = new ArrayList<>();
        private Long id;
        private LocalDate endDate;
        private ProjectStatus status = ProjectStatus.ACTIVE;

        public Builder(String title, LocalDate startDate, List<Professor> advisors) {
            if (title == null || title.isBlank()) {
                throw new InvalidBusinessDataException("O título é obrigatório!");
            }
            this.title = title;
            this.startDate = BusinessValidation.requireNonNull(startDate, "A data de início é obrigatória!");
            BusinessValidation.requireNonNull(advisors, "É obrigatório informar pelo menos um professor orientador!");
            if (advisors.isEmpty()) {
                throw new InvalidBusinessDataException("O projeto deve ter pelo menos um professor orientador.");
            }
            this.advisors = new ArrayList<>();
            advisors.forEach(advisor -> addUnique(this.advisors, advisor, "O professor é obrigatório."));
        }

        public Builder withMembers(List<Student> members) {
            BusinessValidation.requireNonNull(members, "Os participantes são obrigatórios.");
            this.members = new ArrayList<>();
            members.forEach(member -> addUnique(this.members, member, "O aluno é obrigatório."));
            return this;
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
                throw new InvalidBusinessDataException("A data de encerramento não pode ser anterior à data de início.");
            }
        }
    }
}

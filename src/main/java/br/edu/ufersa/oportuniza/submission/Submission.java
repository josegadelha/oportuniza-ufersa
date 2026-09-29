package br.edu.ufersa.oportuniza.submission;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.oportuniza.deliverable.Deliverable;
import br.edu.ufersa.oportuniza.shared.exception.BusinessValidation;
import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;
import br.edu.ufersa.oportuniza.shared.exception.SubmissionRuleViolationException;
import br.edu.ufersa.oportuniza.student.Student;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "submissions", uniqueConstraints = @UniqueConstraint(columnNames = {"deliverable_id", "student_id"}))
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deliverable_id", nullable = false)
    private Deliverable deliverable;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "file_path", length = 255)
    private String filePath;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubmissionStatus status = SubmissionStatus.WAITING;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("versionNumber ASC")
    private List<SubmissionVersion> versions = new ArrayList<>();

    protected Submission() {
    }

    public Submission(Deliverable deliverable, Student student) {
        this.deliverable = BusinessValidation.requireNonNull(deliverable, "A entrega é obrigatória.");
        this.student = BusinessValidation.requireNonNull(student, "O estudante é obrigatório.");
    }

    public void submit(String newFilePath) {
        if (newFilePath == null || newFilePath.isBlank() || newFilePath.length() > 255) {
            throw new InvalidBusinessDataException("O caminho do arquivo deve ter entre 1 e 255 caracteres.");
        }
        if (!status.canTransitionTo(SubmissionStatus.PENDING)) {
            throw new SubmissionRuleViolationException("Esta submissão não aceita novos envios.");
        }
        this.filePath = newFilePath;
        this.submittedAt = LocalDateTime.now();
        this.status = SubmissionStatus.PENDING;
        this.versions.add(new SubmissionVersion(this, versions.size() + 1, newFilePath, submittedAt));
    }

    public void approve() {
        transitionTo(SubmissionStatus.APPROVED);
    }

    public void reject() {
        transitionTo(SubmissionStatus.REJECTED);
    }

    private void transitionTo(SubmissionStatus next) {
        if (!status.canTransitionTo(next)) {
            throw new SubmissionRuleViolationException("Transição inválida da submissão.");
        }
        this.status = next;
    }

    public Long getId() { return id; }
    public Deliverable getDeliverable() { return deliverable; }
    public Student getStudent() { return student; }
    public String getFilePath() { return filePath; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public SubmissionStatus getStatus() { return status; }
    public List<SubmissionVersion> getVersions() { return List.copyOf(versions); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Submission other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }
}

package br.edu.ufersa.oportuniza.submission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDateTime;

@Entity
@Table(name = "submission_versions", uniqueConstraints =
        @UniqueConstraint(columnNames = {"submission_id", "version_number"}))
public class SubmissionVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @Column(name = "version_number", nullable = false)
    private int versionNumber;

    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    protected SubmissionVersion() {
    }

    SubmissionVersion(Submission submission, int versionNumber, String filePath, LocalDateTime submittedAt) {
        this.submission = submission;
        this.versionNumber = versionNumber;
        this.filePath = filePath;
        this.submittedAt = submittedAt;
    }

    public Long getId() { return id; }
    public int getVersionNumber() { return versionNumber; }
    public String getFilePath() { return filePath; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
}

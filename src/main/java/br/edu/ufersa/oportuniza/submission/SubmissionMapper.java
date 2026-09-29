package br.edu.ufersa.oportuniza.submission;

import br.edu.ufersa.oportuniza.deliverable.Deliverable;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionResponse;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionVersionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmissionMapper {

    public Submission toEntity(Deliverable deliverable, Student student) {
        return new Submission(deliverable, student);
    }

    public SubmissionResponse toResponse(Submission submission) {
        if (submission == null) return null;
        return new SubmissionResponse(submission.getId(), submission.getDeliverable().getId(),
                submission.getStudent().getId(), submission.getFilePath(),
                submission.getSubmittedAt(), submission.getStatus());
    }

    public List<SubmissionResponse> toResponseList(List<Submission> submissions) {
        if (submissions == null) return null;
        return submissions.stream().map(this::toResponse).toList();
    }

    public SubmissionVersionResponse toVersionResponse(SubmissionVersion version) {
        if (version == null) return null;
        return new SubmissionVersionResponse(version.getVersionNumber(),
                version.getFilePath(), version.getSubmittedAt());
    }

    public List<SubmissionVersionResponse> toVersionResponseList(List<SubmissionVersion> versions) {
        if (versions == null) return null;
        return versions.stream().map(this::toVersionResponse).toList();
    }
}

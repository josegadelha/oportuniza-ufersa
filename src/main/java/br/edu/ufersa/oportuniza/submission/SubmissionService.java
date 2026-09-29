package br.edu.ufersa.oportuniza.submission;

import br.edu.ufersa.oportuniza.deliverable.Deliverable;
import br.edu.ufersa.oportuniza.project.Project;
import br.edu.ufersa.oportuniza.project.ProjectService;
import br.edu.ufersa.oportuniza.project.ProjectStatus;
import br.edu.ufersa.oportuniza.shared.exception.SubmissionRuleViolationException;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubmissionService {

    private final ProjectService projects;

    public SubmissionService(ProjectService projects) {
        this.projects = projects;
    }

    public Student requireSubmitter(Deliverable deliverable, User actor) {
        Project project = deliverable.getProject();
        if (!(actor instanceof Student student)) {
            throw new AccessDeniedException("Somente estudantes podem enviar entregas.");
        }
        projects.requireMember(project, actor);
        if (project.getStatus() != ProjectStatus.ACTIVE) {
            throw new SubmissionRuleViolationException("O projeto não está ativo.");
        }
        if (deliverable.getDeadline() == null || LocalDate.now().isAfter(deliverable.getDeadline())) {
            throw new SubmissionRuleViolationException("O prazo da entrega terminou.");
        }
        return student;
    }

    public void requireReader(Submission submission, User actor) {
        if (submission.getStudent().getId().equals(actor.getId())) return;
        projects.requireAdvisor(submission.getDeliverable().getProject(), actor);
    }
}

package br.edu.ufersa.oportuniza.submission;

import br.edu.ufersa.oportuniza.deliverable.Deliverable;
import br.edu.ufersa.oportuniza.deliverable.DeliverableRepository;
import br.edu.ufersa.oportuniza.project.ProjectService;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionCreate;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionResponse;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionVersionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubmissionApplicationService {

    private final SubmissionRepository repository;
    private final DeliverableRepository deliverables;
    private final SubmissionService domain;
    private final ProjectService projects;
    private final SubmissionMapper mapper;

    public SubmissionApplicationService(SubmissionRepository repository, DeliverableRepository deliverables,
            SubmissionService domain, ProjectService projects,
            SubmissionMapper mapper) {
        this.repository = repository;
        this.deliverables = deliverables;
        this.domain = domain;
        this.projects = projects;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> listMine(User user) {
        List<Submission> submissions;
        if (user instanceof Student) {
            submissions = repository.findByStudentId(user.getId());
        } else {
            submissions = repository.findAdvisedBy(user.getId());
        }
        return mapper.toResponseList(submissions.stream().distinct().toList());
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> listByDeliverable(Long deliverableId, User user) {
        Deliverable deliverable = findDeliverable(deliverableId);
        projects.requireParticipant(deliverable.getProject(), user);
        if (deliverable.getProject().hasAdvisor(user.getId())) {
            return mapper.toResponseList(repository.findByDeliverableId(deliverableId));
        }
        return repository.findByDeliverableIdAndStudentId(deliverableId, user.getId())
                .stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SubmissionResponse findById(Long id, User user) {
        return mapper.toResponse(findReadable(id, user));
    }

    @Transactional(readOnly = true)
    public List<SubmissionVersionResponse> versions(Long id, User user) {
        return mapper.toVersionResponseList(findReadable(id, user).getVersions());
    }

    @Transactional
    public SaveResult send(Long deliverableId, SubmissionCreate dto, User user) {
        Deliverable deliverable = findDeliverable(deliverableId);
        Student student = domain.requireSubmitter(deliverable, user);
        Submission submission = repository.findByDeliverableIdAndStudentId(deliverableId, student.getId())
                .orElse(null);
        boolean created = submission == null;
        if (created) submission = mapper.toEntity(deliverable, student);
        submission.submit(dto.filePath());
        return new SaveResult(mapper.toResponse(repository.save(submission)), created);
    }

    public record SaveResult(SubmissionResponse response, boolean created) { }

    private Submission findReadable(Long id, User user) {
        Submission submission = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submissão não encontrada."));
        domain.requireReader(submission, user);
        return submission;
    }

    private Deliverable findDeliverable(Long id) {
        return deliverables.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega não encontrada."));
    }
}

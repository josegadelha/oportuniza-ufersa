package br.edu.ufersa.oportuniza.deliverable;

import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableCreate;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverablePatch;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableResponse;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableUpdate;
import br.edu.ufersa.oportuniza.project.Project;
import br.edu.ufersa.oportuniza.project.ProjectService;
import br.edu.ufersa.oportuniza.project.ProjectRepository;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliverableApplicationService {

    private final DeliverableRepository repository;
    private final ProjectRepository projects;
    private final ProjectService projectDomain;
    private final DeliverableMapper mapper;

    public DeliverableApplicationService(DeliverableRepository repository, ProjectRepository projects,
                                         ProjectService projectDomain,
                                         DeliverableMapper mapper) {
        this.repository = repository;
        this.projects = projects;
        this.projectDomain = projectDomain;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<DeliverableResponse> listMine(User user) {
        return mapper.toResponseList(repository.findVisibleTo(user.getId()));
    }

    @Transactional(readOnly = true)
    public List<DeliverableResponse> listByProject(Long projectId, User user) {
        Project project = findProject(projectId);
        projectDomain.requireParticipant(project, user);
        return mapper.toResponseList(repository.findByProjectIdOrderByDeadlineAsc(projectId));
    }

    @Transactional(readOnly = true)
    public DeliverableResponse findById(Long id, User user) {
        Deliverable deliverable = findDeliverable(id);
        projectDomain.requireParticipant(deliverable.getProject(), user);
        return mapper.toResponse(deliverable);
    }

    @Transactional
    public DeliverableResponse create(Long projectId, DeliverableCreate dto, User user) {
        Project project = findProject(projectId);
        projectDomain.requireAdvisor(project, user);
        Deliverable saved = repository.save(mapper.toEntity(dto, project));
        return mapper.toResponse(saved);
    }

    @Transactional
    public DeliverableResponse update(Long id, DeliverableUpdate dto, User user) {
        Deliverable deliverable = findDeliverable(id);
        projectDomain.requireAdvisor(deliverable.getProject(), user);
        mapper.updateEntityFromDto(dto, deliverable);
        return mapper.toResponse(deliverable);
    }

    @Transactional
    public DeliverableResponse patch(Long id, DeliverablePatch dto, User user) {
        Deliverable deliverable = findDeliverable(id);
        projectDomain.requireAdvisor(deliverable.getProject(), user);
        mapper.updateEntityFromDto(dto, deliverable);
        return mapper.toResponse(deliverable);
    }

    private Project findProject(Long id) {
        return projects.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado."));
    }

    private Deliverable findDeliverable(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega não encontrada."));
    }
}

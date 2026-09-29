package br.edu.ufersa.oportuniza.project;

import br.edu.ufersa.oportuniza.professor.Professor;
import br.edu.ufersa.oportuniza.project.dto.ProjectPatch;
import br.edu.ufersa.oportuniza.project.dto.ProjectResponse;
import br.edu.ufersa.oportuniza.project.dto.ProjectUpdate;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.shared.exception.ProjectRuleViolationException;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectApplicationService {

    private final ProjectRepository repository;
    private final ProjectService domain;
    private final ProjectMapper mapper;

    public ProjectApplicationService(ProjectRepository repository, ProjectService domain,
                                     ProjectMapper mapper) {
        this.repository = repository;
        this.domain = domain;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> listMine(User user) {
        List<Project> projects = user instanceof Professor
                ? repository.findByAdvisorsId(user.getId())
                : repository.findByMembersId(user.getId());
        return mapper.toResponseList(projects);
    }

    @Transactional(readOnly = true)
    public ProjectResponse findById(Long projectId, User user) {
        Project project = findProject(projectId);
        domain.requireParticipant(project, user);
        return mapper.toResponse(project);
    }

    @Transactional
    public ProjectResponse update(Long projectId, ProjectUpdate dto, User user) {
        Project project = findProject(projectId);
        domain.requireAdvisor(project, user);
        mapper.updateEntityFromDto(dto, project);
        return mapper.toResponse(project);
    }

    @Transactional
    public ProjectResponse patch(Long projectId, ProjectPatch dto, User user) {
        Project project = findProject(projectId);
        domain.requireAdvisor(project, user);
        mapper.updateEntityFromDto(dto, project);
        return mapper.toResponse(project);
    }

    @Transactional
    public ProjectResponse complete(Long projectId, User user) {
        Project project = findProject(projectId);
        domain.requireAdvisor(project, user);
        project.complete();
        return mapper.toResponse(project);
    }

    @Transactional
    public ProjectResponse cancel(Long projectId, User user) {
        Project project = findProject(projectId);
        domain.requireAdvisor(project, user);
        project.cancel();
        return mapper.toResponse(project);
    }

    // Ponto de integração para a seleção concluída, sem endpoint de criação manual.
    @Transactional
    public Project createFromSelection(String title, LocalDate startDate,
                                       List<Professor> advisors, List<Student> members) {
        if (members == null || members.isEmpty()) {
            throw new ProjectRuleViolationException("O projeto precisa de estudantes selecionados.");
        }
        Project project = mapper.toEntity(title, startDate, advisors, members);
        return repository.save(project);
    }

    private Project findProject(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado."));
    }

}

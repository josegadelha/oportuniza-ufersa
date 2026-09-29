package br.edu.ufersa.oportuniza.projectproposal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalCreate;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalPatch;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalResponse;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalUpdate;
import br.edu.ufersa.oportuniza.proposal.ProposalStatus;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.student.StudentRepository;

@Service
class ProjectProposalApplicationService {

    private final ProjectProposalRepository repository;
    private final StudentRepository students;
    private final ProjectProposalMapper mapper;

    public ProjectProposalApplicationService(
            ProjectProposalRepository repository,
            StudentRepository students,
            ProjectProposalMapper mapper
    ) {
        this.repository = repository;
        this.students = students;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ProjectProposalResponse> list() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<ProjectProposalResponse> listByStudent(Long studentId) {
        findStudent(studentId);
        return mapper.toResponseList(repository.findByStudentId(studentId));
    }

    @Transactional(readOnly = true)
    public ProjectProposalResponse findById(Long projectProposalId) {
        return mapper.toResponse(findProjectProposal(projectProposalId));
    }

    @Transactional
    public ProjectProposalResponse createForStudent(Long studentId, ProjectProposalCreate dto) {
        Student student = findStudent(studentId);
        ProjectProposal saved = repository.save(mapper.toEntity(student, dto));
        return mapper.toResponse(saved);
    }

    @Transactional
    public ProjectProposalResponse update(Long projectProposalId, ProjectProposalUpdate dto) {
        ProjectProposal current = findProjectProposal(projectProposalId);
        Student student = findStudent(dto.studentId());
        ProjectProposal updated = mapper.fromUpdate(current, dto, student);
        return mapper.toResponse(repository.save(updated));
    }

    @Transactional
    public ProjectProposalResponse partialUpdate(Long projectProposalId, ProjectProposalPatch dto) {
        ProjectProposal current = findProjectProposal(projectProposalId);

        Student student = current.getStudent();
        String title = current.getTitle();
        String description = current.getDescription();
        ProposalStatus status = current.getStatus();
        LocalDateTime publishedAt = current.getPublishedAt();
        List<String> desiredSkills = current.getDesiredSkills();

        if (dto.studentId() != null) {
            student = findStudent(dto.studentId());
        }
        if (dto.title() != null) {
            title = dto.title();
        }
        if (dto.description() != null) {
            description = dto.description();
        }
        if (dto.status() != null) {
            status = dto.status();
        }
        if (dto.publishedAt() != null) {
            publishedAt = dto.publishedAt();
        }
        if (dto.desiredSkills() != null) {
            desiredSkills = dto.desiredSkills();
        }

        ProjectProposal updated = new ProjectProposal.Builder(student, title, description)
                .withId(current.getId())
                .withStatus(status)
                .withPublishedAt(publishedAt)
                .withDesiredSkills(desiredSkills)
                .build();

        return mapper.toResponse(repository.save(updated));
    }

    @Transactional
    public void remove(Long projectProposalId) {
        repository.delete(findProjectProposal(projectProposalId));
    }

    private ProjectProposal findProjectProposal(Long projectProposalId) {
        return repository.findById(projectProposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta de projeto não encontrada."));
    }

    private Student findStudent(Long studentId) {
        return students.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado."));
    }
}

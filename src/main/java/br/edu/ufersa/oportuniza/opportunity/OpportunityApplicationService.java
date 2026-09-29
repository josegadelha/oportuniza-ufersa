package br.edu.ufersa.oportuniza.opportunity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityCreate;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityPatch;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityResponse;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityUpdate;
import br.edu.ufersa.oportuniza.professor.Professor;
import br.edu.ufersa.oportuniza.professor.ProfessorRepository;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;

@Service
class OpportunityApplicationService {

    private final OpportunityRepository repository;
    private final ProfessorRepository professors;
    private final OpportunityMapper mapper;

    public OpportunityApplicationService(
            OpportunityRepository repository,
            ProfessorRepository professors,
            OpportunityMapper mapper
    ) {
        this.repository = repository;
        this.professors = professors;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<OpportunityResponse> list() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<OpportunityResponse> listByProfessor(Long professorId) {
        findProfessor(professorId);
        return mapper.toResponseList(repository.findByProfessorId(professorId));
    }

    @Transactional(readOnly = true)
    public OpportunityResponse findById(Long opportunityId) {
        return mapper.toResponse(findOpportunity(opportunityId));
    }

    @Transactional
    public OpportunityResponse createForProfessor(Long professorId, OpportunityCreate dto) {
        Professor professor = findProfessor(professorId);
        Opportunity saved = repository.save(mapper.toEntity(professor, dto));
        return mapper.toResponse(saved);
    }

    @Transactional
    public OpportunityResponse update(Long opportunityId, OpportunityUpdate dto) {
        Opportunity current = findOpportunity(opportunityId);
        Professor professor = current.getProfessor();
        Opportunity updated = mapper.fromUpdate(current, dto, professor);
        return mapper.toResponse(repository.save(updated));
    }

    @Transactional
    public OpportunityResponse partialUpdate(Long opportunityId, OpportunityPatch dto) {
        Opportunity current = findOpportunity(opportunityId);

        String title = current.getTitle();
        String description = current.getDescription();
        OpportunityType type = current.getType();
        Integer positions = current.getPositions();
        Integer workloadHours = current.getWorkloadHours();
        Double remuneration = current.getRemuneration();
        OpportunityStatus status = current.getStatus();
        LocalDateTime publishedAt = current.getPublishedAt();
        LocalDate applicationDeadline = current.getApplicationDeadline();
        List<String> requirements = current.getRequirements();

        if (dto.title() != null) {
            title = dto.title();
        }
        if (dto.description() != null) {
            description = dto.description();
        }
        if (dto.type() != null) {
            type = dto.type();
        }
        if (dto.positions() != null) {
            positions = dto.positions();
        }
        if (dto.workloadHours() != null) {
            workloadHours = dto.workloadHours();
        }
        if (dto.remuneration() != null) {
            remuneration = dto.remuneration();
        }
        if (dto.status() != null) {
            status = dto.status();
        }
        if (dto.publishedAt() != null) {
            publishedAt = dto.publishedAt();
        }
        if (dto.applicationDeadline() != null) {
            applicationDeadline = dto.applicationDeadline();
        }
        if (dto.requirements() != null) {
            requirements = dto.requirements();
        }

        Opportunity updated = new Opportunity.Builder(current.getProfessor(), title, description, type)
                .withId(current.getId())
                .withPositions(positions)
                .withWorkloadHours(workloadHours)
                .withRemuneration(remuneration)
                .withStatus(status)
                .withPublishedAt(publishedAt)
                .withApplicationDeadline(applicationDeadline)
                .withRequirements(requirements)
                .build();

        return mapper.toResponse(repository.save(updated));
    }

    @Transactional
    public void remove(Long opportunityId) {
        repository.delete(findOpportunity(opportunityId));
    }

    private Opportunity findOpportunity(Long opportunityId) {
        return repository.findById(opportunityId)
                .orElseThrow(() -> new ResourceNotFoundException("Oportunidade não encontrada."));
    }

    private Professor findProfessor(Long professorId) {
        return professors.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));
    }
}

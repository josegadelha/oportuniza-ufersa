package br.edu.ufersa.oportuniza.candidacy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyCreate;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyPatch;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyResponse;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyUpdate;
import br.edu.ufersa.oportuniza.opportunity.OpportunityInternalApi;
import br.edu.ufersa.oportuniza.opportunity.OpportunitySummary;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.student.StudentRepository;

@Service
class CandidacyApplicationService {

    private final CandidacyRepository repository;
    private final StudentRepository students;
    private final OpportunityInternalApi opportunityApi;
    private final CandidacyMapper mapper;

    public CandidacyApplicationService(
            CandidacyRepository repository,
            StudentRepository students,
            OpportunityInternalApi opportunityApi,
            CandidacyMapper mapper
    ) {
        this.repository = repository;
        this.students = students;
        this.opportunityApi = opportunityApi;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<CandidacyResponse> list() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<CandidacyResponse> listByOpportunity(Long opportunityId) {
        findOpportunity(opportunityId);
        return mapper.toResponseList(repository.findByOpportunityId(opportunityId));
    }

    @Transactional(readOnly = true)
    public List<CandidacyResponse> listByStudent(Long studentId) {
        findStudent(studentId);
        return mapper.toResponseList(repository.findByStudentId(studentId));
    }

    @Transactional(readOnly = true)
    public CandidacyResponse findById(Long candidacyId) {
        return mapper.toResponse(findCandidacy(candidacyId));
    }

    @Transactional
    public CandidacyResponse createForOpportunity(Long opportunityId, CandidacyCreate dto) {
        OpportunitySummary opportunity = findOpportunity(opportunityId);
        Student student = findStudent(dto.studentId());

        repository.findByStudentIdAndOpportunityId(student.getId(), opportunity.id())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Este estudante já se candidatou a esta oportunidade.");
                });

        Candidacy saved = repository.save(mapper.toEntity(student, opportunity.id(), dto));
        return mapper.toResponse(saved);
    }

    @Transactional
    public CandidacyResponse update(Long candidacyId, CandidacyUpdate dto) {
        Candidacy current = findCandidacy(candidacyId);
        Student student = findStudent(dto.studentId());
        OpportunitySummary opportunity = findOpportunity(dto.opportunityId());

        current.updateStatus(dto.status());
        current = new Candidacy.Builder(student, opportunity.id())
                .withId(current.getId())
                .withAppliedAt(dto.appliedAt())
                .withStatus(dto.status())
                .build();

        return mapper.toResponse(repository.save(current));
    }

    @Transactional
    public CandidacyResponse partialUpdate(Long candidacyId, CandidacyPatch dto) {
        Candidacy current = findCandidacy(candidacyId);

        Student student = current.getStudent();
        Long opportunityId = current.getOpportunityId();
        LocalDateTime appliedAt = current.getAppliedAt();
        CandidacyStatus status = current.getStatus();

        if (dto.studentId() != null) {
            student = findStudent(dto.studentId());
        }
        if (dto.opportunityId() != null) {
            opportunityId = findOpportunity(dto.opportunityId()).id();
        }
        if (dto.appliedAt() != null) {
            appliedAt = dto.appliedAt();
        }
        if (dto.status() != null) {
            status = dto.status();
        }

        Candidacy updated = new Candidacy.Builder(student, opportunityId)
                .withId(current.getId())
                .withAppliedAt(appliedAt)
                .withStatus(status)
                .build();

        return mapper.toResponse(repository.save(updated));
    }

    @Transactional
    public void remove(Long candidacyId) {
        repository.delete(findCandidacy(candidacyId));
    }

    private Candidacy findCandidacy(Long candidacyId) {
        return repository.findById(candidacyId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidatura não encontrada."));
    }

    private Student findStudent(Long studentId) {
        return students.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado."));
    }

    private OpportunitySummary findOpportunity(Long opportunityId) {
        return opportunityApi.findById(opportunityId);
    }
}

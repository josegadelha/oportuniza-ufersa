package br.edu.ufersa.oportuniza.professor;

import br.edu.ufersa.oportuniza.professor.dto.ProfessorCreate;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorPatch;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorResponse;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class ProfessorApplicationService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public ProfessorApplicationService(
            ProfessorRepository professorRepository,
            ProfessorMapper mapper,
            PasswordEncoder passwordEncoder
    ) {
        this.professorRepository = professorRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponse> listAll() {
        List<Professor> professors = professorRepository.findAll();
        return mapper.toResponseList(professors);
    }

    @Transactional(readOnly = true)
    public ProfessorResponse findById(Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();
        return mapper.toResponse(professor);
    }

    @Transactional
    public ProfessorResponse create(ProfessorCreate dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        Professor newProfessor = mapper.toEntity(dto, encodedPassword);

        Professor savedProfessor = professorRepository.save(newProfessor);
        return mapper.toResponse(savedProfessor);
    }

    @Transactional
    public ProfessorResponse update(
            Long professorId,
            ProfessorUpdate dto
    ) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();

        String encodedPassword = passwordEncoder.encode(dto.password());

        mapper.updateEntityFromDto(dto, professor, encodedPassword);

        Professor updatedProfessor = professorRepository.save(professor);
        return mapper.toResponse(updatedProfessor);
    }

    @Transactional
    public ProfessorResponse partialUpdate(
            Long professorId,
            ProfessorPatch dto
    ) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();

        String encodedPassword = dto.password() != null
                ? passwordEncoder.encode(dto.password())
                : null;

        mapper.updateEntityFromDto(dto, professor, encodedPassword);

        Professor updatedProfessor = professorRepository.save(professor);
        return mapper.toResponse(updatedProfessor);
    }

    @Transactional
    public void remove(Long professorId) {
        professorRepository.deleteById(professorId);
    }
}
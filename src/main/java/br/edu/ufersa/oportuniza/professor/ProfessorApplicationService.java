package br.edu.ufersa.oportuniza.professor;

import br.edu.ufersa.oportuniza.professor.dto.ProfessorCreate;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorPatch;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorResponse;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorUpdate;
import br.edu.ufersa.oportuniza.shared.exception.DuplicateResourceException;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.user.Password;
import br.edu.ufersa.oportuniza.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class ProfessorApplicationService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ProfessorApplicationService(
            ProfessorRepository professorRepository,
            ProfessorMapper mapper,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.professorRepository = professorRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponse> listAll() {
        List<Professor> professors = professorRepository.findAll();
        return mapper.toResponseList(professors);
    }

    @Transactional(readOnly = true)
    public ProfessorResponse findById(Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));
        return mapper.toResponse(professor);
    }

    @Transactional
    public ProfessorResponse create(ProfessorCreate dto) {
        new Password(dto.password());
        if (userRepository.existsByUsername(dto.username())) {
            throw new DuplicateResourceException("Nome de usuário já cadastrado.");
        }
        if (userRepository.existsByRegistration_Value(dto.registration())) {
            throw new DuplicateResourceException("Registro já cadastrado.");
        }
        ensureEmailAvailable(dto.email(), null);
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
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));

        new Password(dto.password());
        ensureEmailAvailable(dto.email(), professorId);
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
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));

        if (dto.password() != null) new Password(dto.password());
        if (dto.email() != null) ensureEmailAvailable(dto.email(), professorId);
        String encodedPassword = dto.password() != null
                ? passwordEncoder.encode(dto.password())
                : null;

        mapper.updateEntityFromDto(dto, professor, encodedPassword);

        Professor updatedProfessor = professorRepository.save(professor);
        return mapper.toResponse(updatedProfessor);
    }

    @Transactional
    public void remove(Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));
        professorRepository.delete(professor);
    }

    private void ensureEmailAvailable(String email, Long currentId) {
        boolean exists = currentId == null
                ? userRepository.existsByEmail_Value(email)
                : userRepository.existsByEmail_ValueAndIdNot(email, currentId);
        if (exists) throw new DuplicateResourceException("Email já cadastrado.");
    }
}

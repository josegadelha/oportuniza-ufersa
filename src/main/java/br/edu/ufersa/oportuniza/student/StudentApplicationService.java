package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.student.dto.StudentCreate;
import br.edu.ufersa.oportuniza.student.dto.StudentPatch;
import br.edu.ufersa.oportuniza.student.dto.StudentResponse;
import br.edu.ufersa.oportuniza.student.dto.StudentUpdate;
import br.edu.ufersa.oportuniza.shared.exception.DuplicateResourceException;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.user.Password;
import br.edu.ufersa.oportuniza.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class StudentApplicationService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public StudentApplicationService(
            StudentRepository studentRepository,
            StudentMapper mapper,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> listAll() {
        List<Student> students = studentRepository.findAll();
        return mapper.toResponseList(students);
    }

    @Transactional(readOnly = true)
    public StudentResponse findById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado."));
        return mapper.toResponse(student);
    }

    @Transactional
    public StudentResponse create(StudentCreate dto) {
        new Password(dto.password());
        if (userRepository.existsByUsername(dto.username())) {
            throw new DuplicateResourceException("Nome de usuário já cadastrado.");
        }
        if (userRepository.existsByRegistration_Value(dto.registration())) {
            throw new DuplicateResourceException("Registro já cadastrado.");
        }
        ensureEmailAvailable(dto.email(), null);
        String encodedPassword = passwordEncoder.encode(dto.password());

        Student newStudent = mapper.toEntity(dto, encodedPassword);

        Student savedStudent = studentRepository.save(newStudent);
        return mapper.toResponse(savedStudent);
    }

    @Transactional
    public StudentResponse update(
            Long studentId,
            StudentUpdate dto
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado."));

        new Password(dto.password());
        ensureEmailAvailable(dto.email(), studentId);
        String encodedPassword = passwordEncoder.encode(dto.password());

        mapper.updateEntityFromDto(dto, student, encodedPassword);

        Student updatedStudent = studentRepository.save(student);
        return mapper.toResponse(updatedStudent);
    }

    @Transactional
    public StudentResponse partialUpdate(
            Long studentId,
            StudentPatch dto
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado."));

        if (dto.password() != null) new Password(dto.password());
        if (dto.email() != null) ensureEmailAvailable(dto.email(), studentId);
        String encodedPassword = dto.password() != null
                ? passwordEncoder.encode(dto.password())
                : null;

        mapper.updateEntityFromDto(dto, student, encodedPassword);

        Student updatedStudent = studentRepository.save(student);
        return mapper.toResponse(updatedStudent);
    }

    @Transactional
    public void remove(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado."));
        studentRepository.delete(student);
    }

    private void ensureEmailAvailable(String email, Long currentId) {
        boolean exists = currentId == null
                ? userRepository.existsByEmail_Value(email)
                : userRepository.existsByEmail_ValueAndIdNot(email, currentId);
        if (exists) throw new DuplicateResourceException("Email já cadastrado.");
    }
}

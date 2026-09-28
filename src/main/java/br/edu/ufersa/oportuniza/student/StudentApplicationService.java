package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.student.dto.StudentCreate;
import br.edu.ufersa.oportuniza.student.dto.StudentPatch;
import br.edu.ufersa.oportuniza.student.dto.StudentResponse;
import br.edu.ufersa.oportuniza.student.dto.StudentUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class StudentApplicationService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public StudentApplicationService(
            StudentRepository studentRepository,
            StudentMapper mapper,
            PasswordEncoder passwordEncoder
    ) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> listAll() {
        List<Student> students = studentRepository.findAll();
        return mapper.toResponseList(students);
    }

    @Transactional(readOnly = true)
    public StudentResponse findById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();
        return mapper.toResponse(student);
    }

    @Transactional
    public StudentResponse create(StudentCreate dto) {
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
                .orElseThrow();

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
                .orElseThrow();

        String encodedPassword = dto.password() != null
                ? passwordEncoder.encode(dto.password())
                : null;

        mapper.updateEntityFromDto(dto, student, encodedPassword);

        Student updatedStudent = studentRepository.save(student);
        return mapper.toResponse(updatedStudent);
    }

    @Transactional
    public void remove(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
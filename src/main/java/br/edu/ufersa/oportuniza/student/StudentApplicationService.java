package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.student.dto.StudentCreate;
import br.edu.ufersa.oportuniza.student.dto.StudentPatch;
import br.edu.ufersa.oportuniza.student.dto.StudentResponse;
import br.edu.ufersa.oportuniza.student.dto.StudentUpdate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class StudentApplicationService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    public StudentApplicationService(
            StudentRepository studentRepository,
            StudentMapper mapper
    ) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
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
        Student newStudent = mapper.toEntity(dto);
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
        mapper.updateEntityFromDto(dto, student);
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
        mapper.updateEntityFromDto(dto, student);
        Student updatedStudent = studentRepository.save(student);
        return mapper.toResponse(updatedStudent);
    }

    @Transactional
    public void remove(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}

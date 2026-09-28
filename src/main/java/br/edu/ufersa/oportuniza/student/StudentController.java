package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.student.dto.StudentCreate;
import br.edu.ufersa.oportuniza.student.dto.StudentPatch;
import br.edu.ufersa.oportuniza.student.dto.StudentResponse;
import br.edu.ufersa.oportuniza.student.dto.StudentUpdate;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    private final StudentApplicationService service;

    public StudentController(StudentApplicationService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<StudentResponse>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> findById(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(service.findById(studentId));
    }

    @PostMapping()
    public ResponseEntity<StudentResponse> create(
            @RequestBody @Valid StudentCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        StudentResponse saved = service.create(dto);
        URI uri = uriBuilder
                .path("/api/v1/students/{studentId}")
                .buildAndExpand(saved.id())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentUpdate dto
    ) {
        return ResponseEntity.ok(service.update(studentId, dto));
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentResponse> partialUpdate(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentPatch dto
    ) {
        return ResponseEntity.ok(
                service.partialUpdate(studentId, dto)
        );
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long studentId
    ) {
        service.remove(studentId);
        return ResponseEntity.noContent().build();
    }
}

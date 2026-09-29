package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.student.dto.StudentPatch;
import br.edu.ufersa.oportuniza.student.dto.StudentResponse;
import br.edu.ufersa.oportuniza.student.dto.StudentUpdate;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('STUDENT')")
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    private final StudentApplicationService service;

    public StudentController(StudentApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<StudentResponse> find(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        return ResponseEntity.ok(
                service.findById(authenticatedUser.getUser().getId())
        );
    }

    @PutMapping
    public ResponseEntity<StudentResponse> update(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestBody @Valid StudentUpdate dto
    ) {
        return ResponseEntity.ok(
                service.update(authenticatedUser.getUser().getId(), dto)
        );
    }

    @PatchMapping
    public ResponseEntity<StudentResponse> partialUpdate(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestBody @Valid StudentPatch dto
    ) {
        return ResponseEntity.ok(
                service.partialUpdate(authenticatedUser.getUser().getId(), dto)
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> remove(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        service.remove(authenticatedUser.getUser().getId());

        return ResponseEntity.noContent().build();
    }
}
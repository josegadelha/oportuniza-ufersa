package br.edu.ufersa.oportuniza.professor;

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorPatch;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorResponse;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorUpdate;

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
@PreAuthorize("hasRole('PROFESSOR')")
@RequestMapping("/api/v1/professors")
@Validated
public class ProfessorController {

    private final ProfessorApplicationService service;

    public ProfessorController(ProfessorApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ProfessorResponse> find(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        return ResponseEntity.ok(
                service.findById(authenticatedUser.getUser().getId())
        );
    }

    @PutMapping
    public ResponseEntity<ProfessorResponse> update(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestBody @Valid ProfessorUpdate dto
    ) {
        return ResponseEntity.ok(
                service.update(authenticatedUser.getUser().getId(), dto)
        );
    }

    @PatchMapping
    public ResponseEntity<ProfessorResponse> partialUpdate(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestBody @Valid ProfessorPatch dto
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
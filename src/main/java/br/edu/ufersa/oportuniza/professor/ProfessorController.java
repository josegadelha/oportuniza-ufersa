package br.edu.ufersa.oportuniza.professor;

import br.edu.ufersa.oportuniza.professor.dto.ProfessorCreate;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorPatch;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorResponse;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorUpdate;

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
@RequestMapping("/api/v1/professors")
@Validated
public class ProfessorController {

    private final ProfessorApplicationService service;

    public ProfessorController(ProfessorApplicationService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ProfessorResponse>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorResponse> findById(
            @PathVariable Long professorId
    ) {
        return ResponseEntity.ok(service.findById(professorId));
    }

    @PostMapping()
    public ResponseEntity<ProfessorResponse> create(
            @RequestBody @Valid ProfessorCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        ProfessorResponse saved = service.create(dto);
        URI uri = uriBuilder
                .path("/api/v1/professors/{professorId}")
                .buildAndExpand(saved.id())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{professorId}")
    public ResponseEntity<ProfessorResponse> update(
            @PathVariable Long professorId,
            @RequestBody @Valid ProfessorUpdate dto
    ) {
        return ResponseEntity.ok(service.update(professorId, dto));
    }

    @PatchMapping("/{professorId}")
    public ResponseEntity<ProfessorResponse> partialUpdate(
            @PathVariable Long professorId,
            @RequestBody @Valid ProfessorPatch dto
    ) {
        return ResponseEntity.ok(
                service.partialUpdate(professorId, dto)
        );
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long professorId
    ) {
        service.remove(professorId);
        return ResponseEntity.noContent().build();
    }
}

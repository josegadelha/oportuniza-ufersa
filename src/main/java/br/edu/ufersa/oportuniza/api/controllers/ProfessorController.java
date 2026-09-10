package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.ProfessorCreate;
import br.edu.ufersa.oportuniza.api.dtos.ProfessorPatch;
import br.edu.ufersa.oportuniza.api.dtos.ProfessorResponse;
import br.edu.ufersa.oportuniza.api.dtos.ProfessorUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professors")
@Validated
public class ProfessorController {

    @GetMapping()
    public ResponseEntity<List<ProfessorResponse>> list() {
        return null;
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorResponse> findById(
            @PathVariable Long professorId
    ) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<ProfessorResponse> create(
            @RequestBody @Valid ProfessorCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("{/professorId}")
    public ResponseEntity<ProfessorResponse> update(
            @PathVariable Long professorId,
            @RequestBody @Valid ProfessorUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/{professorId}")
    public ResponseEntity<ProfessorResponse> partialUpdate(
            @PathVariable Long professorId,
            @RequestBody @Valid ProfessorPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long professorId
    ) {
        return null;
    }
}
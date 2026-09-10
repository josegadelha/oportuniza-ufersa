package br.edu.ufersa.oportuniza.api.controllers;

import java.util.List;

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

import br.edu.ufersa.oportuniza.api.dtos.ProfessorCreate;
import br.edu.ufersa.oportuniza.api.dtos.ProfessorPatch;
import br.edu.ufersa.oportuniza.api.dtos.ProfessorResponse;
import br.edu.ufersa.oportuniza.api.dtos.ProfessorUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProfessorController {

    @GetMapping("/professors")
    public ResponseEntity<List<ProfessorResponse>> list() {
        return null;
    }

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<ProfessorResponse> findById(
            @PathVariable Long professorId
    ) {
        return null;
    }

    @PostMapping("/professors")
    public ResponseEntity<ProfessorResponse> create(
            @RequestBody @Valid ProfessorCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/professors/{professorId}")
    public ResponseEntity<ProfessorResponse> update(
            @PathVariable Long professorId,
            @RequestBody @Valid ProfessorUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/professors/{professorId}")
    public ResponseEntity<ProfessorResponse> partialUpdate(
            @PathVariable Long professorId,
            @RequestBody @Valid ProfessorPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/professors/{professorId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long professorId
    ) {
        return null;
    }
}
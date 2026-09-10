package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.ProjectCreate;
import br.edu.ufersa.oportuniza.api.dtos.ProjectPatch;
import br.edu.ufersa.oportuniza.api.dtos.ProjectResponse;
import br.edu.ufersa.oportuniza.api.dtos.ProjectUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProjectController {

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponse>> list() {
        return null;
    }

    @GetMapping("/students/{studentId}/projects")
    public ResponseEntity<List<ProjectResponse>> listByStudent(
            @PathVariable Long studentId
    ) {
        return null;
    }

    @GetMapping("/professors/{professorId}/projects")
    public ResponseEntity<List<ProjectResponse>> listByProfessor(
            @PathVariable Long professorId
    ) {
        return null;
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> findById(
            @PathVariable Long projectId
    ) {
        return null;
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectResponse> create(
            @RequestBody @Valid ProjectCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> partialUpdate(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long projectId
    ) {
        return null;
    }
}
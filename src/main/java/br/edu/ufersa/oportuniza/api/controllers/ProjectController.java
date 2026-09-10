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
@RequestMapping("/api/v1/projects")
@Validated
public class ProjectController {

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> list() {
        return null;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> findById(
            @PathVariable Long projectId
    ) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @RequestBody @Valid ProjectCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> partialUpdate(
            @PathVariable Long projectId,
            @RequestBody @Valid ProjectPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long projectId
    ) {
        return null;
    }
}
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

import br.edu.ufersa.oportuniza.api.dtos.ProjectProposalCreate;
import br.edu.ufersa.oportuniza.api.dtos.ProjectProposalPatch;
import br.edu.ufersa.oportuniza.api.dtos.ProjectProposalResponse;
import br.edu.ufersa.oportuniza.api.dtos.ProjectProposalUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProjectProposalController {

    @GetMapping("/project-proposals")
    public ResponseEntity<List<ProjectProposalResponse>> list() {
        return null;
    }

    @GetMapping("/students/{studentId}/project-proposals")
    public ResponseEntity<List<ProjectProposalResponse>> listByStudent(
            @PathVariable Long studentId
    ) {
        return null;
    }

    @GetMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<ProjectProposalResponse> findById(
            @PathVariable Long projectProposalId
    ) {
        return null;
    }

        @PostMapping("/students/{studentId}/project-proposals")
        public ResponseEntity<ProjectProposalResponse> createForStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid ProjectProposalCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }


    @PutMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<ProjectProposalResponse> update(
            @PathVariable Long projectProposalId,
            @RequestBody @Valid ProjectProposalUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<ProjectProposalResponse> partialUpdate(
            @PathVariable Long projectProposalId,
            @RequestBody @Valid ProjectProposalPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long projectProposalId
    ) {
        return null;
    }
}

package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.CandidacyCreate;
import br.edu.ufersa.oportuniza.api.dtos.CandidacyPatch;
import br.edu.ufersa.oportuniza.api.dtos.CandidacyResponse;
import br.edu.ufersa.oportuniza.api.dtos.CandidacyUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CandidacyController {

    @GetMapping("/candidacies")
    public ResponseEntity<List<CandidacyResponse>> list() {
        return null;
    }

    @GetMapping("/opportunities/{opportunityId}/candidacies")
    public ResponseEntity<List<CandidacyResponse>> listByOpportunity(
            @PathVariable Long opportunityId
    ) {
        return null;
    }

    @GetMapping("/students/{studentId}/candidacies")
    public ResponseEntity<List<CandidacyResponse>> listByStudent(
            @PathVariable Long studentId
    ) {
        return null;
    }

    @GetMapping("/candidacies/{candidacyId}")
    public ResponseEntity<CandidacyResponse> findById(
            @PathVariable Long candidacyId
    ) {
        return null;
    }

    @PostMapping("/opportunities/{opportunityId}/candidacies")
    public ResponseEntity<CandidacyResponse> createForOpportunity(
            @PathVariable Long opportunityId,
            @RequestBody @Valid CandidacyCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/candidacies/{candidacyId}")
    public ResponseEntity<CandidacyResponse> update(
            @PathVariable Long candidacyId,
            @RequestBody @Valid CandidacyUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/candidacies/{candidacyId}")
    public ResponseEntity<CandidacyResponse> partialUpdate(
            @PathVariable Long candidacyId,
            @RequestBody @Valid CandidacyPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/candidacies/{candidacyId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long candidacyId
    ) {
        return null;
    }
}

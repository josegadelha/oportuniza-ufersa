package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.SubmissionCreate;
import br.edu.ufersa.oportuniza.api.dtos.SubmissionResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class SubmissionController {

    @GetMapping("/submissions")
    public ResponseEntity<List<SubmissionResponse>> list() {
        return null;
    }

    @GetMapping("/deliverables/{deliverableId}/submissions")
    public ResponseEntity<List<SubmissionResponse>> listByDeliverable(
            @PathVariable Long deliverableId
    ) {
        return null;
    }

    @GetMapping("/submissions/{submissionId}")
    public ResponseEntity<SubmissionResponse> findById(
            @PathVariable Long submissionId
    ) {
        return null;
    }

    @PostMapping("/deliverables/{deliverableId}/submissions")
    public ResponseEntity<SubmissionResponse> createForDeliverable(
            @PathVariable Long deliverableId,
            @RequestBody @Valid SubmissionCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PatchMapping("/submissions/{submissionId}/approve")
    public ResponseEntity<SubmissionResponse> approve(
            @PathVariable Long submissionId
    ) {
        return null;
    }

    @PatchMapping("/submissions/{submissionId}/reject")
    public ResponseEntity<SubmissionResponse> reject(
            @PathVariable Long submissionId
    ) {
        return null;
    }

    @DeleteMapping("/submissions/{submissionId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long submissionId
    ) {
        return null;
    }
}
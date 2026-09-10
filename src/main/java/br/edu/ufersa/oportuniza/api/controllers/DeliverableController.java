package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.DeliverableCreate;
import br.edu.ufersa.oportuniza.api.dtos.DeliverablePatch;
import br.edu.ufersa.oportuniza.api.dtos.DeliverableResponse;
import br.edu.ufersa.oportuniza.api.dtos.DeliverableUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class DeliverableController {

    @GetMapping("/deliverables")
    public ResponseEntity<List<DeliverableResponse>> list() {
        return null;
    }

    @GetMapping("/projects/{projectId}/deliverables")
    public ResponseEntity<List<DeliverableResponse>> listByProject(
            @PathVariable Long projectId
    ) {
        return null;
    }

    @GetMapping("/deliverables/{deliverableId}")
    public ResponseEntity<DeliverableResponse> findById(
            @PathVariable Long deliverableId
    ) {
        return null;
    }

    @PostMapping("/projects/{projectId}/deliverables")
    public ResponseEntity<DeliverableResponse> createForProject(
            @PathVariable Long projectId,
            @RequestBody @Valid DeliverableCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/deliverables/{deliverableId}")
    public ResponseEntity<DeliverableResponse> update(
            @PathVariable Long deliverableId,
            @RequestBody @Valid DeliverableUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/deliverables/{deliverableId}")
    public ResponseEntity<DeliverableResponse> partialUpdate(
            @PathVariable Long deliverableId,
            @RequestBody @Valid DeliverablePatch dto
    ) {
        return null;
    }

    @DeleteMapping("/deliverables/{deliverableId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long deliverableId
    ) {
        return null;
    }
}
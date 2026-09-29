package br.edu.ufersa.oportuniza.candidacy;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyCreate;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyPatch;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyResponse;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CandidacyController {

    private final CandidacyApplicationService service;

    public CandidacyController(CandidacyApplicationService service) {
        this.service = service;
    }

    @GetMapping("/candidacies")
    public ResponseEntity<List<CandidacyResponse>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/opportunities/{opportunityId}/candidacies")
    public ResponseEntity<List<CandidacyResponse>> listByOpportunity(
            @PathVariable Long opportunityId
    ) {
        return ResponseEntity.ok(service.listByOpportunity(opportunityId));
    }

    @GetMapping("/students/candidacies")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<CandidacyResponse>> listByStudent(
            @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        return ResponseEntity.ok(service.listByStudent(principal.getUser().getId()));
    }

    @GetMapping("/candidacies/{candidacyId}")
    public ResponseEntity<CandidacyResponse> findById(
            @PathVariable Long candidacyId
    ) {
        return ResponseEntity.ok(service.findById(candidacyId));
    }

    @PostMapping("/opportunities/{opportunityId}/candidacies")
    public ResponseEntity<CandidacyResponse> createForOpportunity(
            @PathVariable Long opportunityId,
            @RequestBody @Valid CandidacyCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        CandidacyResponse response = service.createForOpportunity(opportunityId, dto);

        URI uri = uriBuilder.path("/api/v1/candidacies/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/candidacies/{candidacyId}")
    public ResponseEntity<CandidacyResponse> update(
            @PathVariable Long candidacyId,
            @RequestBody @Valid CandidacyUpdate dto
    ) {
        return ResponseEntity.ok(service.update(candidacyId, dto));
    }

    @PatchMapping("/candidacies/{candidacyId}")
    public ResponseEntity<CandidacyResponse> partialUpdate(
            @PathVariable Long candidacyId,
            @RequestBody @Valid CandidacyPatch dto
    ) {
        return ResponseEntity.ok(service.partialUpdate(candidacyId, dto));
    }

    @DeleteMapping("/candidacies/{candidacyId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long candidacyId
    ) {
        service.remove(candidacyId);
        return ResponseEntity.noContent().build();
    }
}

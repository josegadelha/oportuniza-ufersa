package br.edu.ufersa.oportuniza.opportunity;

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
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityCreate;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityPatch;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityResponse;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class OpportunityController {

    private final OpportunityApplicationService service;

    public OpportunityController(OpportunityApplicationService service) {
        this.service = service;
    }

    @GetMapping("/opportunities")
    public ResponseEntity<List<OpportunityResponse>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/professors/opportunities")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<List<OpportunityResponse>> listByProfessor(
            @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        return ResponseEntity.ok(
                service.listByProfessor(
                        principal.getUser().getId()
                )
        );
    }

    @GetMapping("/opportunities/{opportunityId}")
    public ResponseEntity<OpportunityResponse> findById(
            @PathVariable Long opportunityId
    ) {
        return ResponseEntity.ok(
                service.findById(opportunityId)
        );
    }

    @PostMapping("/professors/opportunities")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<OpportunityResponse> createForProfessor(
            @AuthenticationPrincipal AuthenticatedUser principal,
            @RequestBody @Valid OpportunityCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        OpportunityResponse response =
                service.createForProfessor(
                        principal.getUser().getId(),
                        dto
                );

        URI uri = uriBuilder
                .path("/api/v1/opportunities/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/opportunities/{opportunityId}")
    public ResponseEntity<OpportunityResponse> update(
            @PathVariable Long opportunityId,
            @RequestBody @Valid OpportunityUpdate dto
    ) {
        return ResponseEntity.ok(
                service.update(opportunityId, dto)
        );
    }

    @PatchMapping("/opportunities/{opportunityId}")
    public ResponseEntity<OpportunityResponse> partialUpdate(
            @PathVariable Long opportunityId,
            @RequestBody @Valid OpportunityPatch dto
    ) {
        return ResponseEntity.ok(
                service.partialUpdate(opportunityId, dto)
        );
    }

    @DeleteMapping("/opportunities/{opportunityId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long opportunityId
    ) {
        service.remove(opportunityId);
        return ResponseEntity.noContent().build();
    }
}
package br.edu.ufersa.oportuniza.deliverable;

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableCreate;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverablePatch;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableResponse;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliverableController {

    private final DeliverableApplicationService service;

    public DeliverableController(DeliverableApplicationService service) {
        this.service = service;
    }

    @GetMapping("/deliverables")
    public ResponseEntity<List<DeliverableResponse>> list(@AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.listMine(principal.getUser()));
    }

    @GetMapping("/projects/{projectId}/deliverables")
    public ResponseEntity<List<DeliverableResponse>> listByProject(@PathVariable Long projectId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.listByProject(projectId, principal.getUser()));
    }

    @GetMapping("/deliverables/{deliverableId}")
    public ResponseEntity<DeliverableResponse> findById(@PathVariable Long deliverableId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.findById(deliverableId, principal.getUser()));
    }

    @PostMapping("/projects/{projectId}/deliverables")
    public ResponseEntity<DeliverableResponse> createForProject(@PathVariable Long projectId,
            @RequestBody @Valid DeliverableCreate dto, UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        DeliverableResponse saved = service.create(projectId, dto, principal.getUser());
        URI uri = uriBuilder.path("/api/v1/deliverables/{id}").buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/deliverables/{deliverableId}")
    public ResponseEntity<DeliverableResponse> update(@PathVariable Long deliverableId,
            @RequestBody @Valid DeliverableUpdate dto,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.update(deliverableId, dto, principal.getUser()));
    }

    @PatchMapping("/deliverables/{deliverableId}")
    public ResponseEntity<DeliverableResponse> patch(@PathVariable Long deliverableId,
            @RequestBody @Valid DeliverablePatch dto,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.patch(deliverableId, dto, principal.getUser()));
    }
}

package br.edu.ufersa.oportuniza.submission;

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionCreate;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionResponse;
import br.edu.ufersa.oportuniza.submission.dto.SubmissionVersionResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubmissionController {

    private final SubmissionApplicationService service;

    public SubmissionController(SubmissionApplicationService service) {
        this.service = service;
    }

    @GetMapping("/submissions")
    public ResponseEntity<List<SubmissionResponse>> list(@AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.listMine(principal.getUser()));
    }

    @GetMapping("/deliverables/{deliverableId}/submissions")
    public ResponseEntity<List<SubmissionResponse>> listByDeliverable(@PathVariable Long deliverableId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.listByDeliverable(deliverableId, principal.getUser()));
    }

    @GetMapping("/submissions/{submissionId}")
    public ResponseEntity<SubmissionResponse> findById(@PathVariable Long submissionId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.findById(submissionId, principal.getUser()));
    }

    @GetMapping("/submissions/{submissionId}/versions")
    public ResponseEntity<List<SubmissionVersionResponse>> versions(@PathVariable Long submissionId,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.versions(submissionId, principal.getUser()));
    }

    @PostMapping("/deliverables/{deliverableId}/submissions")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<SubmissionResponse> send(@PathVariable Long deliverableId,
            @RequestBody @Valid SubmissionCreate dto, UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal AuthenticatedUser principal) {
        SubmissionApplicationService.SaveResult result = service.send(deliverableId, dto, principal.getUser());
        if (!result.created()) return ResponseEntity.ok(result.response());
        URI uri = uriBuilder.path("/api/v1/submissions/{id}")
                .buildAndExpand(result.response().id()).toUri();
        return ResponseEntity.created(uri).body(result.response());
    }
}

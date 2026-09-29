package br.edu.ufersa.oportuniza.projectproposal;

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
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalCreate;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalPatch;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalResponse;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProjectProposalController {

    private final ProjectProposalApplicationService service;

    public ProjectProposalController(ProjectProposalApplicationService service) {
        this.service = service;
    }

    @GetMapping("/project-proposals")
    public ResponseEntity<List<ProjectProposalResponse>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/students/{studentId}/project-proposals")
    @PreAuthorize ("hasRole('STUDENT')")
    public ResponseEntity<List<ProjectProposalResponse>> listByStudent(
            @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        return ResponseEntity.ok(service.listByStudent(principal.getUser().getId()));
    }

    @GetMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<ProjectProposalResponse> findById(
            @PathVariable Long projectProposalId
    ) {
        return ResponseEntity.ok(service.findById(projectProposalId));
    }

    @PostMapping("/students/{studentId}/project-proposals")
    @PreAuthorize ("hasRole('STUDENT')")
    public ResponseEntity<ProjectProposalResponse> createForStudent(
            @AuthenticationPrincipal AuthenticatedUser principal,
            @RequestBody @Valid ProjectProposalCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        ProjectProposalResponse response = service.createForStudent(principal.getUser().getId(), dto);

        URI uri = uriBuilder.path("/api/v1/project-proposals/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<ProjectProposalResponse> update(
            @PathVariable Long projectProposalId,
            @RequestBody @Valid ProjectProposalUpdate dto
    ) {
        return ResponseEntity.ok(service.update(projectProposalId, dto));
    }

    @PatchMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<ProjectProposalResponse> partialUpdate(
            @PathVariable Long projectProposalId,
            @RequestBody @Valid ProjectProposalPatch dto
    ) {
        return ResponseEntity.ok(service.partialUpdate(projectProposalId, dto));
    }

    @DeleteMapping("/project-proposals/{projectProposalId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long projectProposalId
    ) {
        service.remove(projectProposalId);
        return ResponseEntity.noContent().build();
    }
}

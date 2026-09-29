package br.edu.ufersa.oportuniza.project;

import br.edu.ufersa.oportuniza.auth.AuthenticatedUser;
import br.edu.ufersa.oportuniza.project.dto.ProjectPatch;
import br.edu.ufersa.oportuniza.project.dto.ProjectResponse;
import br.edu.ufersa.oportuniza.project.dto.ProjectUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    private final ProjectApplicationService service;

    public ProjectController(ProjectApplicationService service) {
        this.service = service;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponse>> list(@AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.listMine(principal.getUser()));
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Long projectId,
                                                    @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.findById(projectId, principal.getUser()));
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> update(@PathVariable Long projectId,
                                                  @RequestBody @Valid ProjectUpdate dto,
                                                  @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.update(projectId, dto, principal.getUser()));
    }

    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> patch(@PathVariable Long projectId,
                                                 @RequestBody @Valid ProjectPatch dto,
                                                 @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.patch(projectId, dto, principal.getUser()));
    }

    @PatchMapping("/projects/{projectId}/complete")
    public ResponseEntity<ProjectResponse> complete(@PathVariable Long projectId,
                                                    @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.complete(projectId, principal.getUser()));
    }

    @PatchMapping("/projects/{projectId}/cancel")
    public ResponseEntity<ProjectResponse> cancel(@PathVariable Long projectId,
                                                  @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(service.cancel(projectId, principal.getUser()));
    }
}

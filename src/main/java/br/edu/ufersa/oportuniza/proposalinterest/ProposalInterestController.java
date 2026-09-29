package br.edu.ufersa.oportuniza.proposalinterest;

import java.net.URI;
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

import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestCreate;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestPatch;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestResponse;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProposalInterestController {

    private final ProposalInterestApplicationService service;

    public ProposalInterestController(
            ProposalInterestApplicationService service
    ) {
        this.service = service;
    }

    @GetMapping("/proposal-interests")
    public ResponseEntity<List<ProposalInterestResponse>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/project-proposals/{projectProposalId}/interests")
    public ResponseEntity<List<ProposalInterestResponse>> listByProjectProposal(
            @PathVariable Long projectProposalId
    ) {
        return ResponseEntity.ok(
                service.listByProjectProposal(projectProposalId)
        );
    }

    @GetMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<ProposalInterestResponse> findById(
            @PathVariable Long proposalInterestId
    ) {
        return ResponseEntity.ok(
                service.findById(proposalInterestId)
        );
    }

    @PostMapping("/project-proposals/{projectProposalId}/interests")
    public ResponseEntity<ProposalInterestResponse> createForProjectProposal(
            @PathVariable Long projectProposalId,
            @RequestBody @Valid ProposalInterestCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        ProposalInterestResponse response =
                service.createForProjectProposal(
                        projectProposalId,
                        dto
                );

        URI uri = uriBuilder
                .path("/api/v1/proposal-interests/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<ProposalInterestResponse> update(
            @PathVariable Long proposalInterestId,
            @RequestBody @Valid ProposalInterestUpdate dto
    ) {
        return ResponseEntity.ok(
                service.update(proposalInterestId, dto)
        );
    }

    @PatchMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<ProposalInterestResponse> partialUpdate(
            @PathVariable Long proposalInterestId,
            @RequestBody @Valid ProposalInterestPatch dto
    ) {
        return ResponseEntity.ok(
                service.partialUpdate(proposalInterestId, dto)
        );
    }

    @DeleteMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long proposalInterestId
    ) {
        service.remove(proposalInterestId);

        return ResponseEntity.noContent().build();
    }
}
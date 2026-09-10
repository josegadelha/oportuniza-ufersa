package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestCreate;
import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestPatch;
import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestResponse;
import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProposalInterestController {

    @GetMapping("/proposal-interests")
    public ResponseEntity<List<ProposalInterestResponse>> list() {
        return null;
    }

    @GetMapping("/project-proposals/{projectProposalId}/interests")
    public ResponseEntity<List<ProposalInterestResponse>> listByProjectProposal(
            @PathVariable Long projectProposalId
    ) {
        return null;
    }

    @GetMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<ProposalInterestResponse> findById(
            @PathVariable Long proposalInterestId
    ) {
        return null;
    }

    @PostMapping("/project-proposals/{projectProposalId}/interests")
    public ResponseEntity<ProposalInterestResponse> createForProjectProposal(
            @PathVariable Long projectProposalId,
            @RequestBody @Valid ProposalInterestCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<ProposalInterestResponse> update(
            @PathVariable Long proposalInterestId,
            @RequestBody @Valid ProposalInterestUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<ProposalInterestResponse> partialUpdate(
            @PathVariable Long proposalInterestId,
            @RequestBody @Valid ProposalInterestPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/proposal-interests/{proposalInterestId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long proposalInterestId
    ) {
        return null;
    }
}

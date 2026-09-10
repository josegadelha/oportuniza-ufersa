package br.edu.ufersa.oportuniza.api.controllers;

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

import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestCreate;
import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestPatch;
import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestResponse;
import br.edu.ufersa.oportuniza.api.dtos.ProposalInterestUpdate;
import jakarta.validation.Valid;

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

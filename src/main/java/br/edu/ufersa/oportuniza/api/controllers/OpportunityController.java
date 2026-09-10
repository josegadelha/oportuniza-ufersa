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

import br.edu.ufersa.oportuniza.api.dtos.OpportunityCreate;
import br.edu.ufersa.oportuniza.api.dtos.OpportunityPatch;
import br.edu.ufersa.oportuniza.api.dtos.OpportunityResponse;
import br.edu.ufersa.oportuniza.api.dtos.OpportunityUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/opportunities")
@Validated
public class OpportunityController {

    @GetMapping
    public ResponseEntity<List<OpportunityResponse>> list() {
        return null;
    }

    @GetMapping("/{opportunityId}")
    public ResponseEntity<OpportunityResponse> findById(
            @PathVariable Long opportunityId
    ) {
        return null;
    }

    @PostMapping
    public ResponseEntity<OpportunityResponse> create(
            @RequestBody @Valid OpportunityCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/{opportunityId}")
    public ResponseEntity<OpportunityResponse> update(
            @PathVariable Long opportunityId,
            @RequestBody @Valid OpportunityUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/{opportunityId}")
    public ResponseEntity<OpportunityResponse> partialUpdate(
            @PathVariable Long opportunityId,
            @RequestBody @Valid OpportunityPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/{opportunityId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long opportunityId
    ) {
        return null;
    }
}

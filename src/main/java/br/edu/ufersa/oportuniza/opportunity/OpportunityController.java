package br.edu.ufersa.oportuniza.opportunity;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class OpportunityController {

    @GetMapping("/opportunities")
    public ResponseEntity<List<OpportunityResponse>> list() {
        return null;
    }

    @GetMapping("/professors/{professorId}/opportunities")
    public ResponseEntity<List<OpportunityResponse>> listByProfessor(
            @PathVariable Long professorId
    ) {
        return null;
    }

    @GetMapping("/opportunities/{opportunityId}")
    public ResponseEntity<OpportunityResponse> findById(
            @PathVariable Long opportunityId
    ) {
        return null;
    }

        @PostMapping("/professors/{professorId}/opportunities")
        public ResponseEntity<OpportunityResponse> createForProfessor(
            @PathVariable Long professorId,
            @RequestBody @Valid OpportunityCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/opportunities/{opportunityId}")
    public ResponseEntity<OpportunityResponse> update(
            @PathVariable Long opportunityId,
            @RequestBody @Valid OpportunityUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/opportunities/{opportunityId}")
    public ResponseEntity<OpportunityResponse> partialUpdate(
            @PathVariable Long opportunityId,
            @RequestBody @Valid OpportunityPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/opportunities/{opportunityId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long opportunityId
    ) {
        return null;
    }
}

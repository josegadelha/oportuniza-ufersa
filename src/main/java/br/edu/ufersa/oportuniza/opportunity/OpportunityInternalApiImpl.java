package br.edu.ufersa.oportuniza.opportunity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;

@Service
class OpportunityInternalApiImpl implements OpportunityInternalApi {

    private final OpportunityRepository repository;

    OpportunityInternalApiImpl(OpportunityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public OpportunitySummary findById(Long opportunityId) {
        Opportunity opportunity = repository.findById(opportunityId)
                .orElseThrow(() -> new ResourceNotFoundException("Oportunidade não encontrada."));
        return new OpportunitySummary(
                opportunity.getId(),
                opportunity.getProfessor().getId(),
                opportunity.getStatus()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long opportunityId) {
        return repository.existsById(opportunityId);
    }
}

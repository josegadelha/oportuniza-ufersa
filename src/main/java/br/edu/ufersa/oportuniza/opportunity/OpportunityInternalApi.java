package br.edu.ufersa.oportuniza.opportunity;

public interface OpportunityInternalApi {

    OpportunitySummary findById(Long opportunityId);

    boolean existsById(Long opportunityId);
}

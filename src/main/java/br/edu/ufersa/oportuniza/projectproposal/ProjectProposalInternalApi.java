package br.edu.ufersa.oportuniza.projectproposal;

public interface ProjectProposalInternalApi {

    ProjectProposalSummary findById(Long projectProposalId);

    boolean existsById(Long projectProposalId);
}

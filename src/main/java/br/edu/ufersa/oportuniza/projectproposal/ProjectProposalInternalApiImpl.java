package br.edu.ufersa.oportuniza.projectproposal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;

@Service
class ProjectProposalInternalApiImpl implements ProjectProposalInternalApi {

    private final ProjectProposalRepository repository;

    ProjectProposalInternalApiImpl(ProjectProposalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectProposalSummary findById(Long projectProposalId) {
        ProjectProposal proposal = repository.findById(projectProposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta de projeto não encontrada."));
        return new ProjectProposalSummary(proposal.getId(), proposal.getStudent().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long projectProposalId) {
        return repository.existsById(projectProposalId);
    }
}

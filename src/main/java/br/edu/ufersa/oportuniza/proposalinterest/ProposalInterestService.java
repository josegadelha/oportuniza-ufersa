package br.edu.ufersa.oportuniza.proposalinterest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.edu.ufersa.oportuniza.projectproposal.ProjectProposalInternalApi;
import br.edu.ufersa.oportuniza.user.User;

@Service
class ProposalInterestService {

    private final ProjectProposalInternalApi projectProposalApi;

    ProposalInterestService(ProjectProposalInternalApi projectProposalApi) {
        this.projectProposalApi = projectProposalApi;
    }

    public void requireReader(
        ProposalInterest interest,
        User actor
    ) {
        if (interest.getUser().getId().equals(actor.getId())) {
            return;
        }

        requireProposalOwner(interest.getProjectProposalId(), actor);
    }

    public void requireProposalOwner(
        Long projectProposalId,
        User actor
    ) {
        if (!projectProposalApi.findById(projectProposalId).studentId().equals(actor.getId())) {
            throw new AccessDeniedException(
                "Somente o dono da proposta pode realizar esta operação."
            );
        }
    }

    public void requireInterestOwner(
        ProposalInterest interest,
        User actor
    ) {
        if (!interest.getUser().getId().equals(actor.getId())) {
            throw new AccessDeniedException(
                "Somente o autor do interesse pode realizar esta operação."
            );
        }
    }
}
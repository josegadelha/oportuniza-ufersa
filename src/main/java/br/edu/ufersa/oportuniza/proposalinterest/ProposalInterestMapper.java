package br.edu.ufersa.oportuniza.proposalinterest;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestResponse;
import br.edu.ufersa.oportuniza.user.User;

@Component
class ProposalInterestMapper {

    public ProposalInterest toEntity(
        Long projectProposalId,
        User user
    ) {
        return new ProposalInterest.Builder(projectProposalId, user).build();
    }

    public ProposalInterestResponse toResponse(
        ProposalInterest interest
    ) {
        if (interest == null) {
            return null;
        }

        return new ProposalInterestResponse(
            interest.getId(),
            interest.getProjectProposalId(),
            interest.getUser().getId(),
            interest.getStatus(),
            interest.getCreatedAt()
        );
    }

    public List<ProposalInterestResponse> toResponseList(
        List<ProposalInterest> interests
    ) {
        if (interests == null) {
            return null;
        }

        return interests.stream()
            .map(this::toResponse)
            .toList();
    }
}
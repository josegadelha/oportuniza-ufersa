package br.edu.ufersa.oportuniza.proposalinterest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProposalInterestRepository
        extends JpaRepository<ProposalInterest, Long> {

    List<ProposalInterest> findByUserId(Long userId);

    List<ProposalInterest> findByProjectProposalId(Long projectProposalId);

    Optional<ProposalInterest> findByProjectProposalIdAndUserId(
        Long projectProposalId,
        Long userId
    );

    boolean existsByProjectProposalIdAndUserId(
        Long projectProposalId,
        Long userId
    );
}
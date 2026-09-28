package br.edu.ufersa.oportuniza.proposalinterest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalInterestRepository extends JpaRepository<ProposalInterest, Long> {
}
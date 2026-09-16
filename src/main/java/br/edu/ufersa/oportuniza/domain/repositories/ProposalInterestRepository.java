package br.edu.ufersa.oportuniza.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.ProposalInterest;

@Repository
public interface ProposalInterestRepository extends JpaRepository<ProposalInterest, Long> {
}
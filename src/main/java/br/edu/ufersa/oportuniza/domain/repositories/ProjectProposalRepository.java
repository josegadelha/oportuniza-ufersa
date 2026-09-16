package br.edu.ufersa.oportuniza.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.ProjectProposal;

@Repository
public interface ProjectProposalRepository extends JpaRepository<ProjectProposal, Long> {
}
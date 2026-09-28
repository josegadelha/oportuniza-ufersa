package br.edu.ufersa.oportuniza.projectproposal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectProposalRepository extends JpaRepository<ProjectProposal, Long> {
}
package br.edu.ufersa.oportuniza.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
}
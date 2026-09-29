package br.edu.ufersa.oportuniza.opportunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    java.util.List<Opportunity> findByProfessorId(Long professorId);
}
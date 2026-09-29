package br.edu.ufersa.oportuniza.candidacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CandidacyRepository extends JpaRepository<Candidacy, Long> {

    java.util.List<Candidacy> findByOpportunityId(Long opportunityId);

    java.util.List<Candidacy> findByStudentId(Long studentId);

    java.util.Optional<Candidacy> findByStudentIdAndOpportunityId(Long studentId, Long opportunityId);
}
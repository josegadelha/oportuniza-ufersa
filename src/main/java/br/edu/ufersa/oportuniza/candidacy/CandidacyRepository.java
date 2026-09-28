package br.edu.ufersa.oportuniza.candidacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy, Long> {
}
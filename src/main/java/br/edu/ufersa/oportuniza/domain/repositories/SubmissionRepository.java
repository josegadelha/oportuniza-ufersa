package br.edu.ufersa.oportuniza.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.Submission;
import br.edu.ufersa.oportuniza.domain.entities.SubmissionStatus;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByDeliverableId(Long deliverableId);

    List<Submission> findByDeliverableIdAndStatus(Long deliverableId, SubmissionStatus status);
}
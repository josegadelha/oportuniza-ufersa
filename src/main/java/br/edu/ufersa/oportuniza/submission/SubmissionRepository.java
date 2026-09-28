package br.edu.ufersa.oportuniza.submission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByDeliverableId(Long deliverableId);

    List<Submission> findByDeliverableIdAndStatus(Long deliverableId, SubmissionStatus status);
}
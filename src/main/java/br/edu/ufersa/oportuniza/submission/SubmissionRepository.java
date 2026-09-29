package br.edu.ufersa.oportuniza.submission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByDeliverableId(Long deliverableId);

    List<Submission> findByDeliverableIdAndStatus(Long deliverableId, SubmissionStatus status);

    Optional<Submission> findByDeliverableIdAndStudentId(Long deliverableId, Long studentId);

    List<Submission> findByStudentId(Long studentId);

    @Query("select distinct s from Submission s join s.deliverable d "
            + "join d.project p join p.advisors a where a.id = :professorId")
    List<Submission> findAdvisedBy(@Param("professorId") Long professorId);
}

package br.edu.ufersa.oportuniza.deliverable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {

    List<Deliverable> findByProjectId(Long projectId);

    List<Deliverable> findByProjectIdOrderByDeadlineAsc(Long projectId);
}
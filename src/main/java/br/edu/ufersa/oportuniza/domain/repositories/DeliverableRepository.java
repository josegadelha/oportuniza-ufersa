package br.edu.ufersa.oportuniza.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.Deliverable;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {

    List<Deliverable> findByProjectId(Long projectId);

    List<Deliverable> findByProjectIdOrderByDeadlineAsc(Long projectId);
}
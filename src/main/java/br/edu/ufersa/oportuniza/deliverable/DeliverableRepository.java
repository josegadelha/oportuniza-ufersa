package br.edu.ufersa.oportuniza.deliverable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {

    List<Deliverable> findByProjectId(Long projectId);

    List<Deliverable> findByProjectIdOrderByDeadlineAsc(Long projectId);

    @Query("select distinct d from Deliverable d join d.project p "
            + "left join p.advisors a left join p.members m "
            + "where a.id = :userId or m.id = :userId")
    List<Deliverable> findVisibleTo(@Param("userId") Long userId);
}

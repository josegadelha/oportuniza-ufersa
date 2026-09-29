package br.edu.ufersa.oportuniza.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByStatus(ProjectStatus status);

    List<Project> findByStatusOrderByStartDateDesc(ProjectStatus status);

    List<Project> findByAdvisorsId(Long professorId);

    List<Project> findByMembersId(Long studentId);

    boolean existsByTitleIgnoreCase(String title);
}
package br.edu.ufersa.oportuniza.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.Project;
import br.edu.ufersa.oportuniza.domain.entities.ProjectStatus;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByStatus(ProjectStatus status);

    List<Project> findByStatusOrderByStartDateDesc(ProjectStatus status);

    List<Project> findByAdvisorsId(Long professorId);

    List<Project> findByMembersId(Long studentId);

    boolean existsByTitleIgnoreCase(String title);
}
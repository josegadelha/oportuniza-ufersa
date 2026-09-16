package br.edu.ufersa.oportuniza.domain.repositories;

import br.edu.ufersa.oportuniza.domain.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
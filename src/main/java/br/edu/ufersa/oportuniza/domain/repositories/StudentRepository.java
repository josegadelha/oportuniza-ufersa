package br.edu.ufersa.oportuniza.domain.repositories;

import br.edu.ufersa.oportuniza.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
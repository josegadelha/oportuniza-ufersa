package br.edu.ufersa.oportuniza.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByRegistration_Value(String registration);
    boolean existsByEmail_Value(String email);
    boolean existsByEmail_ValueAndIdNot(String email, Long id);
}

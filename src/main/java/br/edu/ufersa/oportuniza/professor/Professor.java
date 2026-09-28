package br.edu.ufersa.oportuniza.professor;

import br.edu.ufersa.oportuniza.user.Email;
import br.edu.ufersa.oportuniza.user.Password;
import br.edu.ufersa.oportuniza.user.Registration;
import br.edu.ufersa.oportuniza.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_professors")
public class Professor extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;



    protected Professor() {
    }

    private Professor(Builder builder) {
        super(
                builder.id,
                builder.username,
                builder.registration,
                builder.name,
                builder.email,
                builder.password,
                builder.lattesUrl,
                builder.description
        );

        this.department = builder.department;
    }



    public void changeDepartment(Department newDepartment) {
        this.department = Objects.requireNonNull(
                newDepartment,
                "O departamento é obrigatório!"
        );
    }



    public Department getDepartment() {
        return department;
    }



    public static class Builder {

        private Long id;

        private final String username;
        private final Registration registration;
        private final String name;
        private final Email email;
        private final Password password;
        private final Department department;

        private String lattesUrl;
        private String description;



        public Builder(
                String username,
                Registration registration,
                String name,
                Email email,
                Password password,
                Department department
        ) {
            if (username == null || username.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome de usuário é obrigatório!"
                );
            }

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome é obrigatório!"
                );
            }

            this.username = username;

            this.registration = Objects.requireNonNull(
                    registration,
                    "O registro é obrigatório!"
            );

            this.name = name;

            this.email = Objects.requireNonNull(
                    email,
                    "O email é obrigatório!"
            );

            this.password = Objects.requireNonNull(
                    password,
                    "A senha é obrigatória!"
            );

            this.department = Objects.requireNonNull(
                    department,
                    "O departamento é obrigatório!"
            );
        }



        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withLattesUrl(String lattesUrl) {
            this.lattesUrl = lattesUrl;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }



        public Professor build() {
            return new Professor(this);
        }
    }
}
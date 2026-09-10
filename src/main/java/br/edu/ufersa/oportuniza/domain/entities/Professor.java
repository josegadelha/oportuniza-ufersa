package br.edu.ufersa.oportuniza.domain.entities;

import java.util.Objects;

public class Professor extends User {

    private Department department;

    private Professor(Builder builder) {
        super(
                builder.id,
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

        private final Registration registration;
        private final String name;
        private final Email email;
        private final Password password;
        private final Department department;

        private String lattesUrl;
        private String description;

        public Builder(
                Registration registration,
                String name,
                Email email,
                Password password,
                Department department
        ) {
            this.registration = Objects.requireNonNull(
                    registration,
                    "O registro é obrigatório!"
            );

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome é obrigatório!"
                );
            }

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
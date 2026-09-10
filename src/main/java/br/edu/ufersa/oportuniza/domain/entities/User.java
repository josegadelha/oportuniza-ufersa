package br.edu.ufersa.oportuniza.domain.entities;

import java.util.Objects;

public abstract class User {

    private final Long id;
    private final Registration registration;
    private final String name;

    private Email email;
    private Password password;
    private String lattesUrl;
    private String description;

    protected User(
            Long id,
            Registration registration,
            String name,
            Email email,
            Password password,
            String lattesUrl,
            String description
    ) {
        this.id = id;
        this.registration = Objects.requireNonNull(
                registration,
                "O registro é obrigatório!"
        );

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório!");
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

        this.lattesUrl = lattesUrl;
        this.description = description;
    }

    public void changeEmail(Email newEmail) {
        this.email = Objects.requireNonNull(
                newEmail,
                "O novo email é obrigatório!"
        );
    }

    public void changePassword(Password newPassword) {
        this.password = Objects.requireNonNull(
                newPassword,
                "A nova senha é obrigatória!"
        );
    }

    public void updateProfile(
            String lattesUrl,
            String description
    ) {
        this.lattesUrl = lattesUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Registration getRegistration() {
        return registration;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public String getLattesUrl() {
        return lattesUrl;
    }

    public String getDescription() {
        return description;
    }
}
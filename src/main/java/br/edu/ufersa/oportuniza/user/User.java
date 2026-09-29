package br.edu.ufersa.oportuniza.user;

import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;
import br.edu.ufersa.oportuniza.shared.exception.BusinessValidation;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(
                    name = "registration",
                    nullable = false,
                    unique = true
            )
    )
    private Registration registration;

    @Column(nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(
                    name = "email",
                    nullable = false,
                    unique = true
            )
    )
    private Email email;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(
                    name = "password",
                    nullable = false
            )
    )
    private Password password;

    @Column(name = "lattes_url")
    private String lattesUrl;

    @Column
    private String description;



    protected User() {
    }

    protected User(
            Long id,
            String username,
            Registration registration,
            String name,
            Email email,
            Password password,
            String lattesUrl,
            String description
    ) {
        if (username == null || username.isBlank()) {
            throw new InvalidBusinessDataException(
                    "O nome de usuário é obrigatório!"
            );
        }

        if (name == null || name.isBlank()) {
            throw new InvalidBusinessDataException(
                    "O nome é obrigatório!"
            );
        }

        this.id = id;

        this.username = username;

        this.registration = BusinessValidation.requireNonNull(
                registration,
                "O registro é obrigatório!"
        );

        this.name = name;

        this.email = BusinessValidation.requireNonNull(
                email,
                "O email é obrigatório!"
        );

        this.password = BusinessValidation.requireNonNull(
                password,
                "A senha é obrigatória!"
        );

        this.lattesUrl = lattesUrl;
        this.description = description;
    }



    public void changeEmail(Email newEmail) {
        this.email = BusinessValidation.requireNonNull(
                newEmail,
                "O novo email é obrigatório!"
        );
    }

    public void changePassword(Password newPassword) {
        this.password = BusinessValidation.requireNonNull(
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

    public String getUsername() {
        return username;
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

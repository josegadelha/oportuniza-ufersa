package br.edu.ufersa.oportuniza.user;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String value) {

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória!");
        }
        if (value.length() < 6) {
            throw new IllegalArgumentException("A senha deve possuir 6 caracteres ou mais!");
        }
    }
}

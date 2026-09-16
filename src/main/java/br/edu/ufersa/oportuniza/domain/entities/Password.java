package br.edu.ufersa.oportuniza.domain.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String value) {

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória!");
        }
    }
}

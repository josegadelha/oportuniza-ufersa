package br.edu.ufersa.oportuniza.domain.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public record Registration(String value) {

    public Registration {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O registro é obrigatório!");
        }
    }
}

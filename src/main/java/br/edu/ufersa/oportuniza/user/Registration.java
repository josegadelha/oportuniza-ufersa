package br.edu.ufersa.oportuniza.user;

import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;

import jakarta.persistence.Embeddable;

@Embeddable
public record Registration(String value) {

    public Registration {
        if (value == null || value.isBlank()) {
            throw new InvalidBusinessDataException("O registro é obrigatório!");
        }
    }
}

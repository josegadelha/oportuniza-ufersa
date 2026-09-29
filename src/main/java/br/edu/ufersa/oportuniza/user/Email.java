package br.edu.ufersa.oportuniza.user;

import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;

import jakarta.persistence.Embeddable;

@Embeddable
public record Email(String value) {

    public Email {
        if (value == null || value.isBlank()) {
            throw new InvalidBusinessDataException("O email é obrigatório!");
        }

        if (!value.contains("@")) {
            throw new InvalidBusinessDataException("O email é inválido!");
        }
    }
}

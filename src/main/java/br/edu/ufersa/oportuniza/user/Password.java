package br.edu.ufersa.oportuniza.user;

import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String value) {

    public Password {
        if (value == null || value.isBlank()) {
            throw new InvalidBusinessDataException("A senha é obrigatória!");
        }
        if (value.length() < 6) {
            throw new InvalidBusinessDataException("A senha deve possuir 6 caracteres ou mais!");
        }
    }
}

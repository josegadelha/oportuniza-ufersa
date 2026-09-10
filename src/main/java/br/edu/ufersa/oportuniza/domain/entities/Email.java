package br.edu.ufersa.oportuniza.domain.entities;

public record Email(String value) {

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O email é obrigatório!");
        }

        if (!value.contains("@")) {
            throw new IllegalArgumentException("O email é inválido!");
        }
    }
}
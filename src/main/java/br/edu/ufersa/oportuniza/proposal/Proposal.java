package br.edu.ufersa.oportuniza.proposal;

import br.edu.ufersa.oportuniza.shared.exception.InvalidBusinessDataException;

abstract class Proposal {

    protected Proposal() {
    }

    protected static String requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new InvalidBusinessDataException(message);
        }

        return value;
    }
}
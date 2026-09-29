package br.edu.ufersa.oportuniza.proposal;

abstract class Proposal {

    protected Proposal() {
    }

    protected static String requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }

        return value;
    }
}
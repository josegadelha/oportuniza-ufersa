package br.edu.ufersa.oportuniza.shared.exception;

public class DuplicateResourceException extends BusinessRuleViolationException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}

package br.edu.ufersa.oportuniza.shared.exception;

public class BusinessRuleViolationException extends IllegalStateException {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}

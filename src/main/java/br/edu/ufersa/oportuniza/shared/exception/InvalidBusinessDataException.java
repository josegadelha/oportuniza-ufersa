package br.edu.ufersa.oportuniza.shared.exception;

public class InvalidBusinessDataException extends IllegalArgumentException {
    public InvalidBusinessDataException(String message) {
        super(message);
    }
}

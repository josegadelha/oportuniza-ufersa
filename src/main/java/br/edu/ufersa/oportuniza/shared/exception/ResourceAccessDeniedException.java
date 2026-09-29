package br.edu.ufersa.oportuniza.shared.exception;

public class ResourceAccessDeniedException extends RuntimeException {
    public ResourceAccessDeniedException(String message) {
        super(message);
    }
}

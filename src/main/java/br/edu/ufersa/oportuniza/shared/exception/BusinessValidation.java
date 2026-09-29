package br.edu.ufersa.oportuniza.shared.exception;

public final class BusinessValidation {
    private BusinessValidation() {
    }

    public static <T> T requireNonNull(T value, String message) {
        if (value == null) {
            throw new InvalidBusinessDataException(message);
        }
        return value;
    }
}

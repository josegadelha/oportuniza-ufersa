package br.edu.ufersa.oportuniza.domain.entities;

public enum SubmissionStatus {

    WAITING,
    PENDING,
    APPROVED,
    REJECTED;

    public boolean canTransitionTo(SubmissionStatus nextStatus) {
        return switch (this) {
            case WAITING -> nextStatus == PENDING;
            case PENDING -> nextStatus == APPROVED || nextStatus == REJECTED;
            case REJECTED -> nextStatus == PENDING; // reenvio permitido
            case APPROVED -> false; // estado terminal
        };
    }
}
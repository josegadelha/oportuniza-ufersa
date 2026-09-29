package br.edu.ufersa.oportuniza.proposalinterest;

public enum InterestStatus {

    PENDING,
    ACCEPTED,
    REJECTED;

    public boolean canTransitionTo(InterestStatus nextStatus) {
        return switch (this) {
            case PENDING ->
                nextStatus == ACCEPTED || nextStatus == REJECTED;

            case ACCEPTED, REJECTED -> false;
        };
    }
}
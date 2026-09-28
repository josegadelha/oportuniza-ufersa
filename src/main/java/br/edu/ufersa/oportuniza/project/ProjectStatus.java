package br.edu.ufersa.oportuniza.project;

public enum ProjectStatus {
    ACTIVE, COMPLETED, CANCELLED;

    public boolean canTransitionTo(ProjectStatus nextStatus) {
        return switch (this) {
            case ACTIVE -> nextStatus == COMPLETED || nextStatus == CANCELLED;
            case COMPLETED, CANCELLED -> false;
        };
    }
}
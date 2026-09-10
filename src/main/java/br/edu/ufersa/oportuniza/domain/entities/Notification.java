package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Notification {

    private final Long id;
    private final String title;
    private final String message;
    private final NotificationType type;
    private boolean read;
    private final LocalDate sentAt;

    private Notification(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.message = builder.message;
        this.type = builder.type;
        this.read = builder.read;
        this.sentAt = builder.sentAt;
    }

    public void markAsRead() {
        this.read = true;
    }

    public void markAsUnread() {
        this.read = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public boolean isRead() {
        return read;
    }

    public LocalDate getSentAt() {
        return sentAt;
    }

    public static class Builder {

        // Obrigatórios
        private final String title;
        private final String message;
        private final NotificationType type;

        // Opcionais com valores padrão explícitos
        private Long id;
        private boolean read = false;
        private LocalDate sentAt = LocalDate.now();

        public Builder(String title, String message, NotificationType type) {
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("O título é obrigatório!");
            }
            if (message == null || message.isBlank()) {
                throw new IllegalArgumentException("A mensagem é obrigatória!");
            }
            this.title = title;
            this.message = message;
            this.type = Objects.requireNonNull(type, "O tipo da notificação é obrigatório!");
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withRead(boolean read) {
            this.read = read;
            return this;
        }

        public Builder withSentAt(LocalDate sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Notification build() {
            validateInvariants();
            return new Notification(this);
        }

        private void validateInvariants() {
            if (sentAt != null && sentAt.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data de envio não pode ser futura.");
            }
        }
    }
}
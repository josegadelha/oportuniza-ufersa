package br.edu.ufersa.oportuniza.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private NotificationType type;

    @Column(name = "is_read", nullable = false)
    private boolean read;

    @Column(name = "sent_at", nullable = false)
    private LocalDate sentAt;

    protected Notification() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static class Builder {

        //Obrigatórios
        private final String title;
        private final String message;
        private final NotificationType type;

        //Opcionais
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
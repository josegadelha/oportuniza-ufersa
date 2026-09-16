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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private NotificationType type;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "sent_at", nullable = false)
    private LocalDate sentAt;

    protected Notification() {
    }

    private Notification(Builder builder) {
        this.id = builder.id;
        this.recipient = builder.recipient;
        this.title = builder.title;
        this.message = builder.message;
        this.type = builder.type;
        this.isRead = builder.isRead;
        this.sentAt = builder.sentAt;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public void markAsUnread() {
        this.isRead = false;
    }

    public Long getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
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
        return isRead;
    }

    public LocalDate getSentAt() {
        return sentAt;
    }

    public boolean belongsTo(Long recipientId) {
        return recipient != null && recipientId != null && recipientId.equals(recipient.getId());
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
        private final User recipient;
        private final String title;
        private final String message;
        private final NotificationType type;

        //Opcionais
        private Long id;
        private boolean isRead = false;
        private LocalDate sentAt = LocalDate.now();

        public Builder(User recipient, String title, String message, NotificationType type) {
            this.recipient = Objects.requireNonNull(recipient, "O destinatário é obrigatório!");
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

        public Builder withIsRead(boolean isRead) {
            this.isRead = isRead;
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
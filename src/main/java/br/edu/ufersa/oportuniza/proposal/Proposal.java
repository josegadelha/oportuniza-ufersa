package br.edu.ufersa.oportuniza.proposal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposals")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

	@Column(name = "published_at")
	protected LocalDateTime publishedAt;

    protected Proposal() {
    }

    protected Proposal(
        Long id,
        String title,
        String description,
        LocalDateTime publishedAt
    ) {
        this.id = id;
        this.title = requireText(title, "O título é obrigatório!");
        this.description = requireText(
            description,
            "A descrição é obrigatória!"
        );
        this.publishedAt = publishedAt;
    }

    private static String requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }

        return value;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
}
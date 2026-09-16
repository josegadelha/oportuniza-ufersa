package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table (name = "proposals")
public abstract class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private final Long id;

	@Column (nullable = false, length = 150)
	private final String title;

	@Column (nullable = false, length = 500)
	private final String description;

	@Column (name = "published_at", nullable = false)
	protected LocalDateTime publishedAt;

	protected Proposal(Long id, String title, String description, LocalDateTime publishedAt) {
		this.id = id;
		this.title = requireText(title, "O título é obrigatório!");
		this.description = requireText(description, "A descrição é obrigatória!");
		this.publishedAt = publishedAt;
	}

	private static String requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
		return value;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public LocalDateTime getPublishedAt() { return publishedAt; }
}

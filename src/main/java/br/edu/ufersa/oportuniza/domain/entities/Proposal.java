package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDateTime;

public abstract class Proposal {
	private final Long id;

	private final String title;

	private final String description;

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

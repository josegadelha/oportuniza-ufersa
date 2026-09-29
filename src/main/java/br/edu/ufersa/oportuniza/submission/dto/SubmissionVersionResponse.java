package br.edu.ufersa.oportuniza.submission.dto;


import java.time.LocalDateTime;

public record SubmissionVersionResponse(int versionNumber, String filePath, LocalDateTime submittedAt) {
}

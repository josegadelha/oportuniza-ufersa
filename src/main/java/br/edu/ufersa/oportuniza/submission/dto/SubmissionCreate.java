package br.edu.ufersa.oportuniza.submission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SubmissionCreate(

        @NotBlank(message = "O caminho do arquivo é obrigatório!")
        @Size(max = 255, message = "O caminho do arquivo deve ter no máximo 255 caracteres!")
        String filePath
) {
}

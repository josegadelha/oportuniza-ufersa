package br.edu.ufersa.oportuniza.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record SubmissionCreate(

        @NotBlank(message = "O caminho do arquivo é obrigatório!")
        String filePath
) {
}
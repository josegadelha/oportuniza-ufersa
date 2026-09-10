package br.edu.ufersa.oportuniza.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectUpdate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotNull(message = "A data de encerramento é obrigatória!")
        LocalDate endDate
) {
}
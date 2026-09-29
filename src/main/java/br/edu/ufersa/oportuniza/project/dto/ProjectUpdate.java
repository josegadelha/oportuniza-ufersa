package br.edu.ufersa.oportuniza.project.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectUpdate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotNull(message = "A data de encerramento é obrigatória!")
        @FutureOrPresent(message = "A data de encerramento não pode estar no passado!")
        LocalDate endDate
) {
}

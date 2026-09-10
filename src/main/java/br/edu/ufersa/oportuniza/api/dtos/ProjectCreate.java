package br.edu.ufersa.oportuniza.api.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectCreate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotNull(message = "A data de início é obrigatória!")
        LocalDate startDate,

        @FutureOrPresent(message = "A data de encerramento não pode estar no passado!")
        LocalDate endDate
) {
}
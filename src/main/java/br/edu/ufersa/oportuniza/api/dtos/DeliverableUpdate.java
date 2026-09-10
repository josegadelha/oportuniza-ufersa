package br.edu.ufersa.oportuniza.api.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DeliverableUpdate(

        @NotBlank(message = "O título é obrigatório!")
        String title,

        @NotNull(message = "O prazo é obrigatório!")
        @FutureOrPresent(message = "O prazo não pode estar no passado!")
        LocalDate deadline
) {
}
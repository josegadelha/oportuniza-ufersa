package br.edu.ufersa.oportuniza.api.dtos;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public record DeliverablePatch(

        String title,

        @FutureOrPresent(message = "O prazo não pode estar no passado!")
        LocalDate deadline
) {
}
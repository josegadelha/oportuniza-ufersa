package br.edu.ufersa.oportuniza.deliverable.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record DeliverablePatch(

        @Size(min = 1, message = "O título não pode ser vazio!")
        String title,

        @FutureOrPresent(message = "O prazo não pode estar no passado!")
        LocalDate deadline
) {
}

package br.edu.ufersa.oportuniza.project.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectPatch(

        @Size(min = 1, message = "O título não pode ser vazio!")
        String title,

        @FutureOrPresent(message = "A data de encerramento não pode estar no passado!")
        LocalDate endDate
) {
}

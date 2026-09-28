package br.edu.ufersa.oportuniza.project.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public record ProjectPatch(

        String title,

        @FutureOrPresent(message = "A data de encerramento não pode estar no passado!")
        LocalDate endDate
) {
}
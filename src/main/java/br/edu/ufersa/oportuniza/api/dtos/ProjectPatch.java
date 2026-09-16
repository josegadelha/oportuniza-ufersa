package br.edu.ufersa.oportuniza.api.dtos;

import java.time.LocalDate;

import br.edu.ufersa.oportuniza.domain.entities.ProjectStatus;
import jakarta.validation.constraints.FutureOrPresent;

public record ProjectPatch(

        String title,

        @FutureOrPresent(message = "A data de encerramento não pode estar no passado!")
        LocalDate endDate
) {
}
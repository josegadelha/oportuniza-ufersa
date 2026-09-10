package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.ProjectStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ProjectPatch(

        String title,

        LocalDate startDate,

        @FutureOrPresent(message = "A data de encerramento não pode estar no passado!")
        LocalDate endDate,

        ProjectStatus status
) {
}
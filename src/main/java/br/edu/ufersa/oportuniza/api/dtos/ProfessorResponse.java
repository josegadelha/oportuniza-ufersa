package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorResponse(

        @NotNull(message = "O id não pode ser nulo na resposta!")
        Long id,

        @NotBlank(message = "O registro não pode ser vazio na resposta!")
        String registration,

        @NotBlank(message = "O nome não pode ser vazio na resposta!")
        String name,

        @NotBlank(message = "O email não pode ser vazio na resposta!")
        @Email(message = "O email é inválido!")
        String email,

        String lattesUrl,

        String description,

        @NotNull(message = "O departamento não pode ser nulo na resposta!")
        Department department
) {
}
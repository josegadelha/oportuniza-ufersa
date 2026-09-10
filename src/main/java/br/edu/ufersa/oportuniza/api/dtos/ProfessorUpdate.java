package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorUpdate(

        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "O email é inválido!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        String password,

        String lattesUrl,

        String description,

        @NotNull(message = "O departamento é obrigatório!")
        Department department
) {
}
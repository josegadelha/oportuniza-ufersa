package br.edu.ufersa.oportuniza.professor.dto;

import br.edu.ufersa.oportuniza.professor.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorCreate(

        @NotBlank(message = "O nome de usuário é obrigatório!")
        String username,

        @NotBlank(message = "O registro é obrigatório!")
        String registration,

        @NotBlank(message = "O nome é obrigatório!")
        String name,

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
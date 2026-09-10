package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record ProfessorPatch(

        @Email(message = "O email é inválido!")
        @Size(min = 1, message = "O email não pode ser vazio!")
        String email,

        @Size(min = 1, message = "A senha não pode ser vazia!")
        String password,

        String lattesUrl,

        String description,

        Department department
) {
}
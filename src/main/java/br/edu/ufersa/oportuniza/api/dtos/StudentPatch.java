package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Course;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record StudentPatch(

        @Email(message = "O email é inválido!")
        @Size(min = 1, message = "O email não pode ser vazio!")
        String email,

        @Size(min = 1, message = "A senha não pode ser vazia!")
        String password,

        String lattesUrl,

        String description,

        Course course,

        @Min(value = 1, message = "O período atual deve ser maior que zero!")
        Integer currentSemester,

        @Min(value = 0, message = "O IRA deve estar entre 0 e 10!")
        @Max(value = 10, message = "O IRA deve estar entre 0 e 10!")
        Double ira,

        Boolean receiveNotifications
) {
}
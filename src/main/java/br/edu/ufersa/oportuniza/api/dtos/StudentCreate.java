package br.edu.ufersa.oportuniza.api.dtos;

import br.edu.ufersa.oportuniza.domain.entities.Course;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentCreate(

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

        @NotNull(message = "O curso é obrigatório!")
        Course course,

        @NotNull(message = "O período atual é obrigatório!")
        @Min(value = 1, message = "O período atual deve ser maior que zero!")
        Integer currentSemester,

        @NotNull(message = "O IRA é obrigatório!")
        @Min(value = 0, message = "O IRA deve estar entre 0 e 10!")
        @Max(value = 10, message = "O IRA deve estar entre 0 e 10!")
        Double ira,

        Boolean receiveNotifications
) {

    public StudentCreate {
        if (receiveNotifications == null) {
            receiveNotifications = false;
        }
    }
}
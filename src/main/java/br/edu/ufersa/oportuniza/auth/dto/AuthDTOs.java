package br.edu.ufersa.oportuniza.auth.dto;

import jakarta.validation.constraints.NotBlank;

public interface AuthDTOs {

    record LoginRequestDTO(
            @NotBlank(message = "O nome de usuário é obrigatório")
            String username,

            @NotBlank(message = "A senha é obrigatória")
            String password
    ) {
    }

    record TokenResponseDTO(String token) {
    }
}
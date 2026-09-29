package br.edu.ufersa.oportuniza.auth;

import br.edu.ufersa.oportuniza.auth.dto.AuthDTOs;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.TokenResponseDTO> login(
            @RequestBody @Valid AuthDTOs.LoginRequestDTO dto
    ) {
        try {
            String token = authService.login(
                    dto.username(),
                    dto.password()
            );

            return ResponseEntity.ok(
                    new AuthDTOs.TokenResponseDTO(token)
            );

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }
}
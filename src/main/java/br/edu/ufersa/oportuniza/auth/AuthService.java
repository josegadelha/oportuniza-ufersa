package br.edu.ufersa.oportuniza.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String login(String username, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );

        var authentication = authenticationManager.authenticate(
                authenticationToken
        );

        var authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

        return tokenService.generateToken(authenticatedUser.getUser());
    }
}
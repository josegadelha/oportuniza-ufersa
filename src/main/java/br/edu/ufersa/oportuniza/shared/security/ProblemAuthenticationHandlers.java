package br.edu.ufersa.oportuniza.shared.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;

@Component
public class ProblemAuthenticationHandlers {
    private final ObjectMapper objectMapper;

    public ProblemAuthenticationHandlers(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public AuthenticationEntryPoint entryPoint() {
        return (request, response, exception) -> write(request, response, HttpStatus.UNAUTHORIZED,
                "Autenticação necessária para acessar este recurso.");
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, exception) -> write(request, response, HttpStatus.FORBIDDEN,
                "Você não tem permissão para acessar este recurso.");
    }

    private void write(HttpServletRequest request, HttpServletResponse response,
            HttpStatus status, String detail) throws IOException {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setType(URI.create("about:blank"));
        problem.setTitle(status.getReasonPhrase());
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now());

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getOutputStream(), problem);
    }
}

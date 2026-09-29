package br.edu.ufersa.oportuniza.auth;

import br.edu.ufersa.oportuniza.professor.Professor;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;

public enum UserRole {
    STUDENT("ROLE_STUDENT"),
    PROFESSOR("ROLE_PROFESSOR");

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public static UserRole from(User user) {
        if (user instanceof Student) {
            return STUDENT;
        }

        if (user instanceof Professor) {
            return PROFESSOR;
        }

        throw new IllegalArgumentException(
                "Tipo de usuário não reconhecido."
        );
    }
}

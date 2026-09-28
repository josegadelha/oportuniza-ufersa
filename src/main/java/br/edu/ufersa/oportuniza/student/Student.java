package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.user.Email;
import br.edu.ufersa.oportuniza.user.Password;
import br.edu.ufersa.oportuniza.user.Registration;
import br.edu.ufersa.oportuniza.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_students")
public class Student extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Course course;

    @Column(name = "current_period", nullable = false)
    private Integer currentPeriod;

    @Column(nullable = false)
    private Double ira;

    @Column(name = "receive_notifications", nullable = false)
    private boolean receiveNotifications;



    protected Student() {
    }

    private Student(Builder builder) {
        super(
                builder.id,
                builder.username,
                builder.registration,
                builder.name,
                builder.email,
                builder.password,
                builder.lattesUrl,
                builder.description
        );

        this.course = builder.course;
        this.currentPeriod = builder.currentPeriod;
        this.ira = builder.ira;
        this.receiveNotifications = builder.receiveNotifications;
    }



    public void updateAcademicData(
            Course newCourse,
            Integer newCurrentPeriod,
            Double newIra
    ) {
        Objects.requireNonNull(
                newCourse,
                "O curso é obrigatório!"
        );

        validateCurrentPeriod(newCurrentPeriod);
        validateIra(newIra);

        this.course = newCourse;
        this.currentPeriod = newCurrentPeriod;
        this.ira = newIra;
    }

    public void enableOpportunityNotifications() {
        this.receiveNotifications = true;
    }

    public void disableOpportunityNotifications() {
        this.receiveNotifications = false;
    }



    public Course getCourse() {
        return course;
    }

    public Integer getCurrentPeriod() {
        return currentPeriod;
    }

    public Double getIra() {
        return ira;
    }

    public boolean isReceiveNotifications() {
        return receiveNotifications;
    }



    private static void validateCurrentPeriod(
            Integer currentPeriod
    ) {
        if (currentPeriod == null || currentPeriod < 1) {
            throw new IllegalArgumentException(
                    "O período atual deve ser maior que zero!"
            );
        }
    }

    private static void validateIra(Double ira) {
        if (ira == null || ira < 0.0 || ira > 10.0) {
            throw new IllegalArgumentException(
                    "O IRA deve estar entre 0 e 10!"
            );
        }
    }



    public static class Builder {

        private Long id;

        private final String username;
        private final Registration registration;
        private final String name;
        private final Email email;
        private final Password password;
        private final Course course;
        private final Integer currentPeriod;
        private final Double ira;

        private String lattesUrl;
        private String description;
        private boolean receiveNotifications = false;



        public Builder(
                String username,
                Registration registration,
                String name,
                Email email,
                Password password,
                Course course,
                Integer currentPeriod,
                Double ira
        ) {
            if (username == null || username.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome de usuário é obrigatório!"
                );
            }

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome é obrigatório!"
                );
            }

            this.username = username;

            this.registration = Objects.requireNonNull(
                    registration,
                    "O registro é obrigatório!"
            );

            this.name = name;

            this.email = Objects.requireNonNull(
                    email,
                    "O email é obrigatório!"
            );

            this.password = Objects.requireNonNull(
                    password,
                    "A senha é obrigatória!"
            );

            this.course = Objects.requireNonNull(
                    course,
                    "O curso é obrigatório!"
            );

            this.currentPeriod = currentPeriod;
            this.ira = ira;
        }



        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withLattesUrl(String lattesUrl) {
            this.lattesUrl = lattesUrl;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withReceiveNotifications(
                boolean receiveNotifications
        ) {
            this.receiveNotifications = receiveNotifications;
            return this;
        }



        public Student build() {
            validateInvariants();
            return new Student(this);
        }



        private void validateInvariants() {
            validateCurrentPeriod(currentPeriod);
            validateIra(ira);
        }
    }
}

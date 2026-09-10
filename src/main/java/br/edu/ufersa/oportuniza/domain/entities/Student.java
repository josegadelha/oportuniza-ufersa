package br.edu.ufersa.oportuniza.domain.entities;

import java.util.Objects;

public class Student extends User {

    private Course course;
    private Integer currentSemester;
    private Double ira;
    private boolean receiveNotifications;

    private Student(Builder builder) {
        super(
                builder.id,
                builder.registration,
                builder.name,
                builder.email,
                builder.password,
                builder.lattesUrl,
                builder.description
        );

        this.course = builder.course;
        this.currentSemester = builder.currentSemester;
        this.ira = builder.ira;
        this.receiveNotifications = builder.receiveNotifications;
    }

    public void updateAcademicData(
            Course newCourse,
            Integer newCurrentSemester,
            Double newIra
    ) {
        Objects.requireNonNull(
                newCourse,
                "O curso é obrigatório!"
        );

        validateCurrentSemester(newCurrentSemester);
        validateIra(newIra);

        this.course = newCourse;
        this.currentSemester = newCurrentSemester;
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

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public Double getIra() {
        return ira;
    }

    public boolean isReceiveNotifications() {
        return receiveNotifications;
    }

    private static void validateCurrentSemester(
            Integer currentSemester
    ) {
        if (currentSemester == null || currentSemester < 1) {
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

        private final Registration registration;
        private final String name;
        private final Email email;
        private final Password password;
        private final Course course;
        private final Integer currentSemester;
        private final Double ira;

        private String lattesUrl;
        private String description;
        private boolean receiveNotifications = false;

        public Builder(
                Registration registration,
                String name,
                Email email,
                Password password,
                Course course,
                Integer currentSemester,
                Double ira
        ) {
            this.registration = Objects.requireNonNull(
                    registration,
                    "O registro é obrigatório!"
            );

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome é obrigatório!"
                );
            }

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

            this.currentSemester = currentSemester;
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
            validateCurrentSemester(currentSemester);
            validateIra(ira);
        }
    }
}
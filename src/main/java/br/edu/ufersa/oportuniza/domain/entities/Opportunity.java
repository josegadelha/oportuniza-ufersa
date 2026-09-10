package br.edu.ufersa.oportuniza.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Opportunity extends Proposal {

    private final Professor professor;

    private final OpportunityType type;

    private Integer positions;

    private Integer workloadHours;

    private Double remuneration;

    private OpportunityStatus status;

    private LocalDate applicationDeadline;
    private List<String> requirements;

    private Opportunity(Builder builder) {
        super(builder.id, builder.title, builder.description, builder.publishedAt);
        this.professor = builder.professor;
        this.type = builder.type;
        this.positions = builder.positions;
        this.workloadHours = builder.workloadHours;
        this.remuneration = builder.remuneration;
        this.status = builder.status;
        this.applicationDeadline = builder.applicationDeadline;
        this.requirements = new ArrayList<>(builder.requirements);

    }

    public boolean isRemunerated() {
        return remuneration > 0.0;
    }

    public void publish() {
        this.status = OpportunityStatus.OPEN;

        if (this.publishedAt == null) {
            this.publishedAt = LocalDateTime.now();
        }
    }

    public void close() {
        this.status = OpportunityStatus.CLOSED;
    }

    public void finish() {
        this.status = OpportunityStatus.FINISHED;
    }

    public Professor getProfessor() {
        return professor;
    }

    public OpportunityType getType() {
        return type;
    }

    public Integer getPositions() {
        return positions;
    }

    public Integer getWorkloadHours() {
        return workloadHours;
    }

    public Double getRemuneration() {
        return remuneration;
    }

    public OpportunityStatus getStatus() {
        return status;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public List<String> getRequirements() {
        return List.copyOf(requirements);
    }

    public static class Builder {

        private Long id;

        private final Professor professor;
        private final String title;
        private final String description;
        private final OpportunityType type;

        private Integer positions = 1;
        private Integer workloadHours = 0;
        private Double remuneration = 0.0;
        private OpportunityStatus status = OpportunityStatus.DRAFT;
        private LocalDateTime publishedAt;
        private LocalDate applicationDeadline;
        private List<String> requirements = new ArrayList<>();

        public Builder(
                Professor professor,
                String title,
                String description,
                OpportunityType type
        ) {
            this.professor = Objects.requireNonNull(professor, "O professor é obrigatório!");
            this.title = title;
            this.description = description;
            this.type = Objects.requireNonNull(type, "O tipo da oportunidade é obrigatório!");
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withPositions(Integer positions) {
            validatePositions(positions);
            this.positions = positions;
            return this;
        }

        public Builder withWorkloadHours(Integer workloadHours) {
            validateWorkloadHours(workloadHours);
            this.workloadHours = workloadHours;
            return this;
        }

        public Builder withRemuneration(Double remuneration) {
            validateRemuneration(remuneration);
            this.remuneration = remuneration;
            return this;
        }

        public Builder withStatus(OpportunityStatus status) {
            this.status = Objects.requireNonNull(status, "O status é obrigatório!");
            return this;
        }

        public Builder withPublishedAt(LocalDateTime publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        public Builder withApplicationDeadline(
                LocalDate applicationDeadline
        ) {
            this.applicationDeadline = applicationDeadline;
            return this;
        }

        public Builder withRequirements(List<String> requirements) {
            this.requirements =
                    requirements == null
                            ? new ArrayList<>()
                            : new ArrayList<>(requirements);

            return this;
        }

        public Opportunity build() {
            validatePositions(positions);
            validateWorkloadHours(workloadHours);
            validateRemuneration(remuneration);
            return new Opportunity(this);
        }

        private static void validatePositions(Integer positions) {
            if (positions == null || positions < 1) {
                throw new IllegalArgumentException("A quantidade de vagas deve ser maior que zero!");
            }
        }

        private static void validateWorkloadHours(Integer workloadHours) {
            if (workloadHours == null || workloadHours < 0) {
                throw new IllegalArgumentException("A carga horária não pode ser negativa!");
            }
        }

        private static void validateRemuneration(Double remuneration) {
            if (remuneration == null || remuneration < 0.0) {
                throw new IllegalArgumentException("A remuneração não pode ser negativa!");
            }
        }
    }
}

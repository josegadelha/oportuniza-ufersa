package br.edu.ufersa.oportuniza.opportunity;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityCreate;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityResponse;
import br.edu.ufersa.oportuniza.opportunity.dto.OpportunityUpdate;
import br.edu.ufersa.oportuniza.professor.Professor;

@Component
class OpportunityMapper {

    public Opportunity toEntity(Professor professor, OpportunityCreate dto) {
        if (dto == null) {
            return null;
        }

        return new Opportunity.Builder(
                professor,
                dto.title(),
                dto.description(),
                dto.type()
        )
                .withPositions(dto.positions())
                .withWorkloadHours(dto.workloadHours())
                .withRemuneration(dto.remuneration())
                .withStatus(dto.status() != null ? dto.status() : OpportunityStatus.DRAFT)
                .withPublishedAt(dto.publishedAt())
                .withApplicationDeadline(dto.applicationDeadline())
                .withRequirements(dto.requirements())
                .build();
    }

    public OpportunityResponse toResponse(Opportunity opportunity) {
        if (opportunity == null) {
            return null;
        }

        return new OpportunityResponse(
                opportunity.getId(),
                opportunity.getProfessor().getId(),
                opportunity.getTitle(),
                opportunity.getDescription(),
                opportunity.getType(),
                opportunity.getPositions(),
                opportunity.getWorkloadHours(),
                opportunity.getRemuneration(),
                opportunity.getStatus(),
                opportunity.getPublishedAt(),
                opportunity.getApplicationDeadline(),
                opportunity.getRequirements()
        );
    }

    public List<OpportunityResponse> toResponseList(List<Opportunity> opportunities) {
        if (opportunities == null) {
            return null;
        }

        return opportunities.stream().map(this::toResponse).toList();
    }

    public Opportunity fromUpdate(Opportunity current, OpportunityUpdate dto, Professor professor) {
        return new Opportunity.Builder(
                professor,
                dto.title(),
                dto.description(),
                dto.type()
        )
                .withId(current.getId())
                .withPositions(dto.positions())
                .withWorkloadHours(dto.workloadHours())
                .withRemuneration(dto.remuneration())
                .withStatus(dto.status())
                .withPublishedAt(dto.publishedAt() != null ? dto.publishedAt() : current.getPublishedAt())
                .withApplicationDeadline(dto.applicationDeadline())
                .withRequirements(dto.requirements())
                .build();
    }
}

package br.edu.ufersa.oportuniza.candidacy;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyCreate;
import br.edu.ufersa.oportuniza.candidacy.dto.CandidacyResponse;
import br.edu.ufersa.oportuniza.student.Student;

@Component
class CandidacyMapper {

    public Candidacy toEntity(Student student, Long opportunityId, CandidacyCreate dto) {
        if (dto == null) {
            return null;
        }

        return new Candidacy.Builder(student, opportunityId)
                .withAppliedAt(dto.appliedAt() != null ? dto.appliedAt() : java.time.LocalDateTime.now())
                .withStatus(dto.status() != null ? dto.status() : CandidacyStatus.IN_SELECTION)
                .build();
    }

    public CandidacyResponse toResponse(Candidacy candidacy) {
        if (candidacy == null) {
            return null;
        }

        return new CandidacyResponse(
                candidacy.getId(),
                candidacy.getStudent().getId(),
                candidacy.getOpportunityId(),
                candidacy.getAppliedAt(),
                candidacy.getStatus()
        );
    }

    public List<CandidacyResponse> toResponseList(List<Candidacy> candidacies) {
        if (candidacies == null) {
            return null;
        }

        return candidacies.stream().map(this::toResponse).toList();
    }
}

package br.edu.ufersa.oportuniza.projectproposal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalCreate;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalResponse;
import br.edu.ufersa.oportuniza.projectproposal.dto.ProjectProposalUpdate;
import br.edu.ufersa.oportuniza.proposal.ProposalStatus;
import br.edu.ufersa.oportuniza.student.Student;

@Component
class ProjectProposalMapper {

    public ProjectProposal toEntity(Student student, ProjectProposalCreate dto) {
        if (dto == null) {
            return null;
        }

        return new ProjectProposal.Builder(student, dto.title(), dto.description())
                .withStatus(dto.status() != null ? dto.status() : ProposalStatus.OPEN)
                .withPublishedAt(dto.publishedAt() != null ? dto.publishedAt() : LocalDateTime.now())
                .withDesiredSkills(dto.desiredSkills())
                .build();
    }

    public ProjectProposalResponse toResponse(ProjectProposal projectProposal) {
        if (projectProposal == null) {
            return null;
        }

        return new ProjectProposalResponse(
                projectProposal.getId(),
                projectProposal.getStudent().getId(),
                projectProposal.getTitle(),
                projectProposal.getDescription(),
                projectProposal.getStatus(),
                projectProposal.getPublishedAt(),
                projectProposal.getDesiredSkills()
        );
    }

    public List<ProjectProposalResponse> toResponseList(List<ProjectProposal> projectProposals) {
        if (projectProposals == null) {
            return null;
        }

        return projectProposals.stream().map(this::toResponse).toList();
    }

    public ProjectProposal fromUpdate(ProjectProposal current, ProjectProposalUpdate dto, Student student) {
        return new ProjectProposal.Builder(student, dto.title(), dto.description())
                .withId(current.getId())
                .withStatus(dto.status())
                .withPublishedAt(dto.publishedAt() != null ? dto.publishedAt() : current.getPublishedAt())
                .withDesiredSkills(dto.desiredSkills())
                .build();
    }
}

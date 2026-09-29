package br.edu.ufersa.oportuniza.deliverable;

import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableCreate;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverablePatch;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableResponse;
import br.edu.ufersa.oportuniza.deliverable.dto.DeliverableUpdate;
import br.edu.ufersa.oportuniza.project.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliverableMapper {

    public Deliverable toEntity(DeliverableCreate dto, Project project) {
        if (dto == null) return null;
        return new Deliverable.Builder(project, dto.title()).withDeadline(dto.deadline()).build();
    }

    public DeliverableResponse toResponse(Deliverable deliverable) {
        if (deliverable == null) return null;
        return new DeliverableResponse(deliverable.getId(), deliverable.getProject().getId(),
                deliverable.getTitle(), deliverable.getDeadline());
    }

    public List<DeliverableResponse> toResponseList(List<Deliverable> deliverables) {
        if (deliverables == null) return null;
        return deliverables.stream().map(this::toResponse).toList();
    }

    public void updateEntityFromDto(DeliverableUpdate dto, Deliverable deliverable) {
        deliverable.renameTitle(dto.title());
        deliverable.postponeDeadline(dto.deadline());
    }

    public void updateEntityFromDto(DeliverablePatch dto, Deliverable deliverable) {
        if (dto.title() != null) deliverable.renameTitle(dto.title());
        if (dto.deadline() != null) deliverable.postponeDeadline(dto.deadline());
    }
}

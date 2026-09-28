package br.edu.ufersa.oportuniza.professor;

import br.edu.ufersa.oportuniza.professor.dto.ProfessorCreate;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorPatch;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorResponse;
import br.edu.ufersa.oportuniza.professor.dto.ProfessorUpdate;
import br.edu.ufersa.oportuniza.user.Email;
import br.edu.ufersa.oportuniza.user.Password;
import br.edu.ufersa.oportuniza.user.Registration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    default Professor toEntity(ProfessorCreate dto) {
        if (dto == null) {
            return null;
        }

        return new Professor.Builder(
                dto.username(),
                new Registration(dto.registration()),
                dto.name(),
                new Email(dto.email()),
                new Password(dto.password()),
                dto.department()
        )
                .withLattesUrl(dto.lattesUrl())
                .withDescription(dto.description())
                .build();
    }

    default ProfessorResponse toResponse(Professor entity) {
        if (entity == null) {
            return null;
        }

        return new ProfessorResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getRegistration().value(),
                entity.getName(),
                entity.getEmail().value(),
                entity.getLattesUrl(),
                entity.getDescription(),
                entity.getDepartment()
        );
    }

    default List<ProfessorResponse> toResponseList(
            List<Professor> entities
    ) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponse)
                .toList();
    }

    default void updateEntityFromDto(
            ProfessorUpdate dto,
            Professor entity
    ) {
        entity.changeEmail(new Email(dto.email()));
        entity.changePassword(new Password(dto.password()));
        entity.updateProfile(
                dto.lattesUrl(),
                dto.description()
        );
        entity.changeDepartment(dto.department());
    }

    default void updateEntityFromDto(
            ProfessorPatch dto,
            Professor entity
    ) {
        if (dto.email() != null) {
            entity.changeEmail(new Email(dto.email()));
        }

        if (dto.password() != null) {
            entity.changePassword(new Password(dto.password()));
        }

        if (dto.lattesUrl() != null
                || dto.description() != null) {
            entity.updateProfile(
                    dto.lattesUrl() != null
                            ? dto.lattesUrl()
                            : entity.getLattesUrl(),
                    dto.description() != null
                            ? dto.description()
                            : entity.getDescription()
            );
        }

        if (dto.department() != null) {
            entity.changeDepartment(dto.department());
        }
    }
}
package br.edu.ufersa.oportuniza.student;

import br.edu.ufersa.oportuniza.student.dto.StudentCreate;
import br.edu.ufersa.oportuniza.student.dto.StudentPatch;
import br.edu.ufersa.oportuniza.student.dto.StudentResponse;
import br.edu.ufersa.oportuniza.student.dto.StudentUpdate;
import br.edu.ufersa.oportuniza.user.Email;
import br.edu.ufersa.oportuniza.user.Password;
import br.edu.ufersa.oportuniza.user.Registration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    default Student toEntity(StudentCreate dto) {
        if (dto == null) {
            return null;
        }

        return new Student.Builder(
                dto.username(),
                new Registration(dto.registration()),
                dto.name(),
                new Email(dto.email()),
                new Password(dto.password()),
                dto.course(),
                dto.currentPeriod(),
                dto.ira()
        )
                .withLattesUrl(dto.lattesUrl())
                .withDescription(dto.description())
                .withReceiveNotifications(dto.receiveNotifications())
                .build();
    }

    default StudentResponse toResponse(Student entity) {
        if (entity == null) {
            return null;
        }

        return new StudentResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getRegistration().value(),
                entity.getName(),
                entity.getEmail().value(),
                entity.getLattesUrl(),
                entity.getDescription(),
                entity.getCourse(),
                entity.getCurrentPeriod(),
                entity.getIra(),
                entity.isReceiveNotifications()
        );
    }

    default List<StudentResponse> toResponseList(List<Student> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponse)
                .toList();
    }

    default void updateEntityFromDto(
            StudentUpdate dto,
            Student entity
    ) {
        entity.changeEmail(new Email(dto.email()));
        entity.changePassword(new Password(dto.password()));
        entity.updateProfile(dto.lattesUrl(), dto.description());
        entity.updateAcademicData(
                dto.course(),
                dto.currentPeriod(),
                dto.ira()
        );

        if (dto.receiveNotifications()) {
            entity.enableOpportunityNotifications();
        } else {
            entity.disableOpportunityNotifications();
        }
    }

    default void updateEntityFromDto(
            StudentPatch dto,
            Student entity
    ) {
        if (dto.email() != null) {
            entity.changeEmail(new Email(dto.email()));
        }

        if (dto.password() != null) {
            entity.changePassword(new Password(dto.password()));
        }

        if (dto.lattesUrl() != null || dto.description() != null) {
            entity.updateProfile(
                    dto.lattesUrl() != null
                            ? dto.lattesUrl()
                            : entity.getLattesUrl(),
                    dto.description() != null
                            ? dto.description()
                            : entity.getDescription()
            );
        }

        if (dto.course() != null
                || dto.currentPeriod() != null
                || dto.ira() != null) {
            entity.updateAcademicData(
                    dto.course() != null
                            ? dto.course()
                            : entity.getCourse(),
                    dto.currentPeriod() != null
                            ? dto.currentPeriod()
                            : entity.getCurrentPeriod(),
                    dto.ira() != null
                            ? dto.ira()
                            : entity.getIra()
            );
        }

        if (dto.receiveNotifications() != null) {
            if (dto.receiveNotifications()) {
                entity.enableOpportunityNotifications();
            } else {
                entity.disableOpportunityNotifications();
            }
        }
    }
}
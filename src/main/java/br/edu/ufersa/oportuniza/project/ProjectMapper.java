package br.edu.ufersa.oportuniza.project;

import br.edu.ufersa.oportuniza.professor.Professor;
import br.edu.ufersa.oportuniza.project.dto.ProjectPatch;
import br.edu.ufersa.oportuniza.project.dto.ProjectResponse;
import br.edu.ufersa.oportuniza.project.dto.ProjectUpdate;
import br.edu.ufersa.oportuniza.student.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProjectMapper {

    public Project toEntity(String title, LocalDate startDate,
                             List<Professor> advisors, List<Student> members) {
        return new Project.Builder(title, startDate, advisors).withMembers(members).build();
    }

    public ProjectResponse toResponse(Project project) {
        if (project == null) return null;
        return new ProjectResponse(project.getId(), project.getTitle(), project.getStartDate(),
                project.getEndDate(), project.getStatus(),
                project.getAdvisors().stream().map(Professor::getId).toList(),
                project.getMembers().stream().map(Student::getId).toList());
    }

    public List<ProjectResponse> toResponseList(List<Project> projects) {
        if (projects == null) return null;
        return projects.stream().map(this::toResponse).toList();
    }

    public void updateEntityFromDto(ProjectUpdate dto, Project project) {
        project.renameTitle(dto.title());
        project.extendEndDate(dto.endDate());
    }

    public void updateEntityFromDto(ProjectPatch dto, Project project) {
        if (dto.title() != null) project.renameTitle(dto.title());
        if (dto.endDate() != null) project.extendEndDate(dto.endDate());
    }
}

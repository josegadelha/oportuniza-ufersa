package br.edu.ufersa.oportuniza.project;

import br.edu.ufersa.oportuniza.user.User;
import br.edu.ufersa.oportuniza.shared.exception.ResourceAccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    public void requireParticipant(Project project, User user) {
        if (!project.hasAdvisor(user.getId()) && !project.hasMember(user.getId())) {
            throw new ResourceAccessDeniedException("Somente participantes podem acessar este projeto.");
        }
    }

    public void requireAdvisor(Project project, User user) {
        if (!project.hasAdvisor(user.getId())) {
            throw new ResourceAccessDeniedException("Somente orientadores podem alterar este projeto.");
        }
    }

    public void requireMember(Project project, User user) {
        if (!project.hasMember(user.getId())) {
            throw new ResourceAccessDeniedException("Somente estudantes participantes podem enviar entregas.");
        }
    }
}

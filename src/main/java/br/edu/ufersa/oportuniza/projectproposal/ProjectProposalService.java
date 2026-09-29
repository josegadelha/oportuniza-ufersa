package br.edu.ufersa.oportuniza.projectproposal;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;

@Service
class ProjectProposalService {

    public Student requireOwner(ProjectProposal projectProposal, User actor) {
        if (!(actor instanceof Student student)) {
            throw new AccessDeniedException("Somente o estudante dono da proposta pode realizar esta operação.");
        }

        if (!projectProposal.getStudent().getId().equals(student.getId())) {
            throw new AccessDeniedException("Você não é o dono desta proposta de projeto.");
        }

        return student;
    }

    public void requireReader(ProjectProposal projectProposal, User actor) {
        if (projectProposal.getStudent().getId().equals(actor.getId())) {
            return;
        }
    }
}

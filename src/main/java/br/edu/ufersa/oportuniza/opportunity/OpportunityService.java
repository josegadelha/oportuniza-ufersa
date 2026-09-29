package br.edu.ufersa.oportuniza.opportunity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.edu.ufersa.oportuniza.professor.Professor;
import br.edu.ufersa.oportuniza.user.User;

@Service
class OpportunityService {

    public Professor requireOwner(Opportunity opportunity, User actor) {
        if (!(actor instanceof Professor professor)) {
            throw new AccessDeniedException("Somente o professor responsável pode realizar esta operação.");
        }

        if (!opportunity.getProfessor().getId().equals(professor.getId())) {
            throw new AccessDeniedException("Você não é o responsável por esta oportunidade.");
        }

        return professor;
    }

    public void requireReader(Opportunity opportunity, User actor) {
        if (opportunity.getProfessor().getId().equals(actor.getId())) {
            return;
        }
    }
}

package br.edu.ufersa.oportuniza.candidacy;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.edu.ufersa.oportuniza.opportunity.OpportunityInternalApi;
import br.edu.ufersa.oportuniza.opportunity.OpportunitySummary;
import br.edu.ufersa.oportuniza.student.Student;
import br.edu.ufersa.oportuniza.user.User;

@Service
class CandidacyService {

    private final OpportunityInternalApi opportunityApi;

    CandidacyService(OpportunityInternalApi opportunityApi) {
        this.opportunityApi = opportunityApi;
    }

    public Student requireStudentOwner(Candidacy candidacy, User actor) {
        if (!(actor instanceof Student student)) {
            throw new AccessDeniedException("Somente o estudante titular da candidatura pode realizar esta operação.");
        }

        if (!candidacy.getStudent().getId().equals(student.getId())) {
            throw new AccessDeniedException("Você não é o estudante desta candidatura.");
        }

        return student;
    }

    public void requireReader(Candidacy candidacy, User actor) {
        if (candidacy.getStudent().getId().equals(actor.getId())) {
            return;
        }

        if (opportunityApi.findById(candidacy.getOpportunityId()).professorId().equals(actor.getId())) {
            return;
        }
    }

    public void requireOpportunityOwner(Candidacy candidacy, User actor) {
        OpportunitySummary opportunity = opportunityApi.findById(candidacy.getOpportunityId());
        if (!opportunity.professorId().equals(actor.getId())) {
            throw new AccessDeniedException("Somente o professor responsável pode avaliar esta candidatura.");
        }
    }
}

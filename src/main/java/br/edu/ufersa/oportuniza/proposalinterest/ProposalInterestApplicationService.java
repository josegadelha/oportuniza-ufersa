package br.edu.ufersa.oportuniza.proposalinterest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.oportuniza.projectproposal.ProjectProposalInternalApi;
import br.edu.ufersa.oportuniza.projectproposal.ProjectProposalSummary;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestCreate;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestPatch;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestResponse;
import br.edu.ufersa.oportuniza.proposalinterest.dto.ProposalInterestUpdate;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.user.User;
import br.edu.ufersa.oportuniza.user.UserRepository;

@Service
class ProposalInterestApplicationService {

    private final ProposalInterestRepository repository;
    private final ProjectProposalInternalApi projectProposalApi;
    private final UserRepository userRepository;
    private final ProposalInterestMapper mapper;

    public ProposalInterestApplicationService(
            ProposalInterestRepository repository,
            ProjectProposalInternalApi projectProposalApi,
            UserRepository userRepository,
            ProposalInterestMapper mapper
    ) {
        this.repository = repository;
        this.projectProposalApi = projectProposalApi;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ProposalInterestResponse> list() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<ProposalInterestResponse> listByProjectProposal(
            Long projectProposalId
    ) {
        findProjectProposal(projectProposalId);

        return mapper.toResponseList(
                repository.findByProjectProposalId(projectProposalId)
        );
    }

    @Transactional(readOnly = true)
    public ProposalInterestResponse findById(
            Long proposalInterestId
    ) {
        return mapper.toResponse(
                findProposalInterest(proposalInterestId)
        );
    }

    @Transactional
    public ProposalInterestResponse createForProjectProposal(
            Long projectProposalId,
            ProposalInterestCreate dto
    ) {
        ProjectProposalSummary projectProposal =
                findProjectProposal(projectProposalId);

        User user = findUser(dto.userId());

        ProposalInterest.Builder builder =
                new ProposalInterest.Builder(
                        projectProposal.id(),
                        user
                );

        if (dto.status() != null) {
            builder.withStatus(dto.status());
        }

        if (dto.createdAt() != null) {
            builder.withCreatedAt(dto.createdAt());
        }

        ProposalInterest proposalInterest =
                builder.build();

        ProposalInterest saved =
                repository.save(proposalInterest);

        return mapper.toResponse(saved);
    }

    @Transactional
    public ProposalInterestResponse update(
            Long proposalInterestId,
            ProposalInterestUpdate dto
    ) {
        findProposalInterest(proposalInterestId);

        ProjectProposalSummary projectProposal =
                findProjectProposal(dto.projectProposalId());

        User user =
                findUser(dto.userId());

        ProposalInterest updated =
                new ProposalInterest.Builder(
                        projectProposal.id(),
                        user
                )
                        .withId(proposalInterestId)
                        .withStatus(dto.status())
                        .withCreatedAt(dto.createdAt())
                        .build();

        ProposalInterest saved =
                repository.save(updated);

        return mapper.toResponse(saved);
    }

    @Transactional
    public ProposalInterestResponse partialUpdate(
            Long proposalInterestId,
            ProposalInterestPatch dto
    ) {
        ProposalInterest current =
                findProposalInterest(proposalInterestId);

        Long projectProposalId = current.getProjectProposalId();

        User user =
                current.getUser();

        InterestStatus status =
                current.getStatus();

        LocalDateTime createdAt =
                current.getCreatedAt();

        if (dto.projectProposalId() != null) {
            projectProposalId =
                    findProjectProposal(dto.projectProposalId()).id();
        }

        if (dto.userId() != null) {
            user =
                    findUser(dto.userId());
        }

        if (dto.status() != null) {
            status = dto.status();
        }

        if (dto.createdAt() != null) {
            createdAt = dto.createdAt();
        }

        ProposalInterest updated =
                new ProposalInterest.Builder(
                        projectProposalId,
                        user
                )
                        .withId(proposalInterestId)
                        .withStatus(status)
                        .withCreatedAt(createdAt)
                        .build();

        ProposalInterest saved =
                repository.save(updated);

        return mapper.toResponse(saved);
    }

    @Transactional
    public void remove(
            Long proposalInterestId
    ) {
        ProposalInterest proposalInterest =
                findProposalInterest(proposalInterestId);

        repository.delete(proposalInterest);
    }

    private ProposalInterest findProposalInterest(
            Long proposalInterestId
    ) {
        return repository
                .findById(proposalInterestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Interesse em proposta não encontrado."
                        )
                );
    }

        private ProjectProposalSummary findProjectProposal(
            Long projectProposalId
    ) {
        return projectProposalApi.findById(projectProposalId);
    }

    private User findUser(
            Long userId
    ) {
        return userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Usuário não encontrado."
                        )
                );
    }
}
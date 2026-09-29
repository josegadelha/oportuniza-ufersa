package br.edu.ufersa.oportuniza.notification;

import br.edu.ufersa.oportuniza.notification.dto.NotificationResponse;
import br.edu.ufersa.oportuniza.shared.exception.ResourceNotFoundException;
import br.edu.ufersa.oportuniza.user.User;
import br.edu.ufersa.oportuniza.shared.exception.ResourceAccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationApplicationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public NotificationApplicationService(NotificationRepository repository, NotificationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> listMine(User user) {
        return mapper.toResponseList(repository.findByRecipientIdOrderBySentAtDesc(user.getId()));
    }

    @Transactional(readOnly = true)
    public NotificationResponse findById(Long id, User user) {
        return mapper.toResponse(findMine(id, user));
    }

    @Transactional
    public NotificationResponse markAsRead(Long id, User user) {
        Notification notification = findMine(id, user);
        notification.markAsRead();
        return mapper.toResponse(notification);
    }

    // Outros casos de uso podem emitir notificações sem expor criação ao cliente HTTP.
    @Transactional
    public NotificationResponse notifyUser(User recipient, String title, String message, NotificationType type) {
        Notification saved = repository.save(mapper.toEntity(recipient, title, message, type));
        return mapper.toResponse(saved);
    }

    private Notification findMine(Long id, User user) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada."));
        if (!notification.belongsTo(user.getId())) {
            throw new ResourceAccessDeniedException("Esta notificação pertence a outro usuário.");
        }
        return notification;
    }
}

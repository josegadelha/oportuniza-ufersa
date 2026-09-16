package br.edu.ufersa.oportuniza.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.oportuniza.domain.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByIsRead(boolean isRead);

    List<Notification> findAllByOrderBySentAtDesc();

    List<Notification> findByRecipientIdOrderBySentAtDesc(Long recipientId);

    List<Notification> findByRecipientIdAndIsRead(Long recipientId, boolean isRead);
}
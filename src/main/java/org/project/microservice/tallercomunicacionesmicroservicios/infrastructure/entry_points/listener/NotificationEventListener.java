package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.listener;

import lombok.RequiredArgsConstructor;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.NotificationChannel;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.usecase.NotificationUseCase;
import org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.listener.request.SendNotificationRequest;
import org.reactivecommons.api.domain.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;



@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationEventListener.class);
    private final NotificationUseCase useCase;

    public Mono<Void> sendNotification(DomainEvent<SendNotificationRequest> ev) {
        SendNotificationRequest dto = ev.getData();
        log.info("User created: {}", dto);
        return useCase.sendNotification(buildNotification(dto)).then();
    }

    private Notification buildNotification(SendNotificationRequest dto) {
        SendNotificationRequest.Content content = getContent(dto);
        return Notification.builder()
                .asunto(content.subject())
                .htmlBody(content.htmlBody())
                .toSms(getSms(dto))
                .toWhatsapp(getWhatsapp(dto))
                .emailUser(getEmail(dto))
                .textBody(content.textBody())
                .build();
    }

    private SendNotificationRequest.Content getContent(SendNotificationRequest dto) {
        return dto.contents().stream().findFirst().isPresent() ? dto.contents().stream().findFirst().get() : SendNotificationRequest.Content.builder().build();
    }
    private String getEmail(SendNotificationRequest dto) {
        return dto.recipients().get(NotificationChannel.EMAIL.name());
    }
    private String getSms(SendNotificationRequest dto) {
        return dto.recipients().get(NotificationChannel.SMS.name());
    }
    private String getWhatsapp(SendNotificationRequest dto) {
        return dto.recipients().get(NotificationChannel.WHATSAPP.name());
    }


}
package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.driven_adapter.send_notification.notification;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.NotificationChannel;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.gateway.NotificationGateway;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class SendNotificationAdapter implements NotificationGateway {


    private final JavaMailSender javaMailSender;

    @Override
    public NotificationChannel channel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public Mono<Void> deliver(Notification message) {
        return Mono.fromRunnable(() -> {
                    try {
                        MimeMessage mime = javaMailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(mime, "UTF-8");
                        helper.setSubject(message.getAsunto());
                        helper.setText(message.getHtmlBody(), true); // true = HTML
                        helper.setTo(message.getEmailUser());
                        helper.setFrom("no_reply@dominio.com");
                        javaMailSender.send(mime);

                    } catch (Exception e) {
                        throw new RuntimeException("Error enviando correo", e);
                    }
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then(); // Mono<Void>
    }


}

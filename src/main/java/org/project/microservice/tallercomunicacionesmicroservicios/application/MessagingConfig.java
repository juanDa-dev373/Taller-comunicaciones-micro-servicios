package org.project.microservice.tallercomunicacionesmicroservicios.application;

import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.usecase.NotificationUseCase;
import org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.listener.NotificationEventListener;
import org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.entry_points.listener.request.SendNotificationRequest;
import org.reactivecommons.async.api.HandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MessagingConfig {

    @Bean
    @Primary
    public HandlerRegistry handlerRegistry(NotificationEventListener listener) {
        return HandlerRegistry.register()
                .listenEvent("notification.", listener::sendNotification, SendNotificationRequest.class);
    }


}

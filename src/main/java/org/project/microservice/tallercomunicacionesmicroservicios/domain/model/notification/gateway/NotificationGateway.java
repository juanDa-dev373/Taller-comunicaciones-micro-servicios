package org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.gateway;

import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.NotificationChannel;
import reactor.core.publisher.Mono;

public interface NotificationGateway {
    NotificationChannel channel();
    Mono<Void> deliver(Notification message);

}

package org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.gateway;

import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationRepositoryGateway {
    Mono<Notification> save(Notification notification);
    Flux<Notification> search(String q, int limit, int offset);
    Flux<Notification> findByStatus(String status);
}

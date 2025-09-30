package org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.driven_adapter.jpa_repository.notification;

import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.Notification;
import org.project.microservice.tallercomunicacionesmicroservicios.domain.model.notification.gateway.NotificationRepositoryGateway;
import org.project.microservice.tallercomunicacionesmicroservicios.infrastructure.driven_adapter.jpa_repository.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class NotificationRepositoryAdapter extends ReactiveAdapterOperations<Notification, NotificationEntity, Long, NotificationRepository>
implements NotificationRepositoryGateway {

    public NotificationRepositoryAdapter(NotificationRepository repository, ObjectMapper mapper) {
        super(repository, mapper, n -> mapper.map(n, Notification.class));
    }

    @Override
    public Flux<Notification> search(String q, int limit, int offset) {
        return repository.search(q, limit, offset).map(this::toEntity);
    }

    @Override
    public Flux<Notification> findByStatus(String status) {
        return repository.findByStatus(status).map(this::toEntity);
    }
}
